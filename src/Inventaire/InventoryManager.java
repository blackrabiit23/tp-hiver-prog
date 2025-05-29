/**
 * Classe pour gerer l'inventaire des articles.
 *
 * @author El khazraji Brahim
 * @version 1.0
 */
package Inventaire;
import Exceptions.ExceptionInsufficientQuantityInStock;
import Exceptions.ExceptionItemAlreadyExists;
import Exceptions.ExceptionItemNotFound;
import Item.*;

public class InventoryManager {
    private InventoryDatabase inventoryDatabase;

    /**
     * Constructeur de l'inventaire
     */
    public InventoryManager(){
        this.inventoryDatabase = new InventoryDatabase();
    }

    /**
     *  Methode pour ajouter un item pain a l'inventaire
     *
     * @param categori Categorie de l'item a ajouter
     * @param ID identifiant de l'item a ajouter
     * @param name nom de l'item a ajouter
     * @param price prix de l'item a ajouter
     * @param color couleur de l'item a ajouter
     * @param weight poid de l'item a ajouter
     * @throws ExceptionItemAlreadyExists lance une exception si l'item existe deja
     */
    public void addNewBreadItem(Categori categori, int ID , String name , double price , String color , int weight) throws ExceptionItemAlreadyExists{
        ItemBread bread = new ItemBread(categori,ID , name ,price ,color ,weight);
        inventoryDatabase.insert(bread);
    }

    /**
     * Methode pour ajouter un item oeuf a l'inventaire
     *
     * @param categori Categorie de l'item a ajouter
     * @param ID identifiant de l'item a ajouter
     * @param name nom de l'item a ajouter
     * @param price prix de l'item a ajouter
     * @param color couleur de l'item oeuf a ajouter
     * @param number nombre de l'item oeuf a ajouter
     * @throws ExceptionItemAlreadyExists lance une exception si l'item existe deja
     */
    public void addNewEggsItem(Categori categori, int ID , String name , double price , String color , int number) throws ExceptionItemAlreadyExists{
        ItemEggs egg = new ItemEggs(categori,ID ,name ,price ,color ,number);
        inventoryDatabase.insert(egg);
    }

    /**
     *
     * Methode pour ajouter un item lait a l'inventaire
     *
     * @param categori Categorie de l'item a ajouter
     * @param ID identifiant de l'item a ajouter
     * @param name nom de l'item a ajouter
     * @param price prix de l'item a ajouter
     * @param fat gras de l'item lait
     * @param liters liters de l'item lait
     * @throws ExceptionItemAlreadyExists lance une exception si l'item existe deja
     */
    public void addNewMilkItem(Categori categori, int ID , String name , double price , double fat , double liters) throws ExceptionItemAlreadyExists{
        ItemMilk milk = new ItemMilk(categori,ID ,name ,price ,fat , liters);
        inventoryDatabase.insert(milk);
    }

    /**
     * Enlever un item de l'inventaire
     *
     * @param ID identifient de l'item
     * @throws ExceptionItemNotFound lance une exception si l'item n'existe pas
     */
    public void removeItem(int ID) throws ExceptionItemNotFound{ // methode ne marche a revoire
        inventoryDatabase.remove(ID);
    }

    /**
     * Augmenter la quantiter de l'item
     *
     * @param ID identifiant de l'item
     * @param quantity quantite qu'on souhaite ajouter
     * @throws ExceptionItemNotFound lance une exception si l'item n'existe pas
     */
    public void increaseItemQuantity(int ID , int quantity) throws ExceptionItemNotFound{
        inventoryDatabase.findByID(ID).increaseQuantityStock(quantity);
    }

    /**
     * Diminuer la quantiter de l'item
     * @param ID identifiant de l'item
     * @param quantity quantite qu'on souhaite enlever.
     */
    public void decreaseItemQuantity(int ID , int quantity){
        if (inventoryDatabase.findByID(ID) == null) {
            throw new ExceptionItemNotFound(ID);
        }
        if (inventoryDatabase.findByID(ID).getQuantityInStock() < quantity)
            throw new ExceptionInsufficientQuantityInStock(quantity);

        inventoryDatabase.findByID(ID).decreaseQuantityStock(quantity);
    }

    /**
     *
     * @param ID identifiant de l'item
     * @return null si l'item existe pas
     */
    public Item getItem(int ID){
        try {
            return inventoryDatabase.findByID(ID);
        } catch (ExceptionItemNotFound e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return null;
    }

    /**
     * Transforme la structure de donnes des items (liste chainees) en tableau([])
     *
     * @return la liste d'item en tableau
     */
    public Item[] getArrayOfItems(){
        return inventoryDatabase.getArrayList();
    }
}