import GUI.GUIInventoryManager;
import Inventaire.InventoryManager;
import Item.*;
import Exceptions.*;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingMain {

    public static void main(String[] args) throws Exception {
        //
        // TODO -- Dé-commentez les lignes autres que //IO// et //G// au fur et à mesure de votre implémentation
        //         Éventuellement, tous les tests devraient fonctionner et vous devriez
        //         obtenir le même affichage que celui montré dans l'énoncé
        // TODO --  Dé-commentez les lignes //IO// pour tester les deux fonction de lecture et d'écriture
        //          Vous devriez obtenir le format de fichier montré dans items.in et items.out
        // TODO -- Dé-commentez la ligne //G// pur tester votre implémentation graphique
        //         Éventuellement, vous devriez obtenir le même résultat que dans le clip de l'énoncé

        InventoryManager inventoryManager = new InventoryManager();
        lireInventaire("items.in", inventoryManager);                                          // 9 points
        System.out.println("\n=> TEST Création de nouveaux items");                                 // 6 points
        inventoryManager.addNewBreadItem(Categori.Bread, 10, "Pain brun riche", 2.45, "brun", 200);
        inventoryManager.addNewBreadItem(Categori.Bread, 11, "Pain blanc traditionnel", 1.50, "blanc", 200);
        inventoryManager.addNewEggsItem(Categori.Eggs, 12, "Oeufs de poules en liberté", 3.50, "Brun", 12);
        inventoryManager.addNewMilkItem(Categori.Milk, 13, "Lait bio très gras", 8.45, 3.8, 2);

        System.out.println("\n=> TEST Trouver un item et afficher l'information sur cet item");     // 6 points
        Item item1 = inventoryManager.getItem(10);
        System.out.println(item1.infoToString());

        System.out.println("\n=> TEST Création d'un item avec un ID existant");                     // 6 points
        try {
            inventoryManager.addNewBreadItem(Categori.Bread, 10, "Pain bio", 5, "brun", 400);
        } catch (ExceptionItemAlreadyExists e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Enlever un item");                                            // 6 points
        inventoryManager.removeItem(10);

        System.out.println("\n=> TEST Enlever un item non existant (catch exception)");             // 6 points
        try {
            inventoryManager.removeItem(10);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Incrémenter la quantité d'un item");                          // 8 points
        try {
            inventoryManager.increaseItemQuantity(10, 18);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            inventoryManager.increaseItemQuantity(11, 3);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            inventoryManager.increaseItemQuantity(12, 4);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            inventoryManager.increaseItemQuantity(13, 23);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Incrementer la quantité d'un item et afficher les nouvelles informations"); // 6 points
        inventoryManager.increaseItemQuantity(11, 25);
        Item item2 = inventoryManager.getItem(11);
        System.out.println(item2.infoToString());

        System.out.println("\n=> TEST Incrementer la quantité d'un item et afficher les nouvelles informations");// 6 points
        inventoryManager.increaseItemQuantity(11, 3);
        Item item3 = inventoryManager.getItem(11);
        System.out.println(item3.infoToString());

        System.out.println("\n=> Décrementer la quantité d'un item non existant (catch exception)");// 6 points
        try {
            inventoryManager.decreaseItemQuantity(10, 1);
        } catch (ExceptionItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Trop décrémenter la quantité d'un item (catch exception)");   // 6 points
        try {
            inventoryManager.decreaseItemQuantity(11, 32);
        } catch (ExceptionInsufficientQuantityInStock e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=> TEST Récupérer le array de items");// points
        Item[] items = inventoryManager.getArrayOfItems();
        for (Item item : items) {
            System.out.println(item.infoToString());
        }
        ecrireInventaire("items.out",inventoryManager);                                       // 9 points

        GUIInventoryManager GUIInventoryManager = new GUIInventoryManager(inventoryManager);   // 20 points


    }

    public static void lireInventaire(String chemin, InventoryManager inventoryManager) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            Pattern motif = Pattern.compile("Catégorie \\[(.*?)\\] ID \\[(\\d+)\\] Nom \\[(.*?)\\] Prix \\[(.*?)\\](.*)");
            while ((ligne = lecteur.readLine()) != null) {
                Matcher matche = motif.matcher(ligne);
                if (matche.find()) {
                    String categorie = matche.group(1);
                    int id = Integer.parseInt(matche.group(2));
                    String nom = matche.group(3);
                    double prix = Double.parseDouble(matche.group(4));
                    String suite = matche.group(5).trim();


                    switch (categorie) {
                        case "Milk":
                            Matcher Milkmatcher = Pattern.compile("Gras \\[(.*?)\\] Litres \\[(.*?)\\]").matcher(suite);
                            if (Milkmatcher.find()) {
                                double gras = Double.parseDouble(Milkmatcher.group(1));
                                double liters = Double.parseDouble(Milkmatcher.group(2));
                                try {
                                    inventoryManager.addNewMilkItem(Categori.Milk, id, nom, prix, gras, liters);
                                }catch (ExceptionItemAlreadyExists e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;
                        case "Eggs":
                            Matcher EggsMatcher = Pattern.compile("Couleur \\[(.*?)\\] Nombre \\[(.*?)\\]").matcher(suite);
                            if (EggsMatcher.find()) {
                                String color = EggsMatcher.group(1);
                                int nombre = Integer.parseInt(EggsMatcher.group(2));
                                try {
                                    inventoryManager.addNewEggsItem(Categori.Eggs, id, nom, prix, color, nombre);
                                }catch (ExceptionItemAlreadyExists e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;
                        case "Bread":
                            Matcher BreadMatcher = Pattern.compile("Couleur \\[(.*?)\\] Poids \\[(.*?)\\]").matcher(suite);
                            if (BreadMatcher.find()) {
                                String color = BreadMatcher.group(1);
                                int Poids = Integer.parseInt(BreadMatcher.group(2));
                                try {
                                    inventoryManager.addNewBreadItem(Categori.Bread, id, nom, prix, color, Poids);
                                }catch (ExceptionItemAlreadyExists e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void  ecrireInventaire(String chemin , InventoryManager inventoryManager){
        try(BufferedWriter ecrivain = new BufferedWriter(new FileWriter(chemin))){
            Item[] liste = inventoryManager.getArrayOfItems();
            for(int i = 0 ; i < liste.length ; i++){
                ecrivain.write(
                        "Categorie "+"["+liste[i].getCategoryString()+"] "
                +"ID "+"["+liste[i].getID()+"] "+"Nom "+"["+liste[i].getName()+"] "+"Prix "+"["+liste[i].getPrice()+"]"+"\s");
                if(liste[i] instanceof ItemBread)
                    ecrivain.write("Couleur "+ "["+((ItemBread) liste[i]).getColor() +"]" + "Poids" +"["+((ItemBread) liste[i]).getWeight() +"]"+"\s");
                else if (liste[i] instanceof ItemEggs)
                    ecrivain.write("Coleur "+ "["+((ItemEggs) liste[i]).getColor()+"]"+" Nombre"+"["+((ItemEggs) liste[i]).getNumber()+"]"+"\s");
                else if (liste[i]instanceof ItemMilk)
                    ecrivain.write("Gras "+"["+((ItemMilk) liste[i]).getFat()+"] "+"Litres "+"["+ ((ItemMilk) liste[i]).getLiters()+"]"+"\s");
                ecrivain.newLine();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
