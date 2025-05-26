package Inventaire;
import Exceptions.ExceptionInsufficientQuantityInStock;
import Exceptions.ExceptionItemAlreadyExists;
import Exceptions.ExceptionItemNotFound;
import Item.*;

public class InventoryManager {
    private InventoryDatabase inventoryDatabase;

    public InventoryManager(){
        this.inventoryDatabase = new InventoryDatabase();
    }

    public void addNewBreadItem(Categori categori, int ID , String name , double price , String color , int weight) throws ExceptionItemAlreadyExists{
        ItemBread bread = new ItemBread(categori,ID , name ,price ,color ,weight);
        inventoryDatabase.insert(bread);
    }
    public void addNewEggsItem(Categori categori, int ID , String name , double price , String color , int number) throws ExceptionItemAlreadyExists{
        ItemEggs egg = new ItemEggs(categori,ID ,name ,price ,color ,number);
        inventoryDatabase.insert(egg);
    }
    public void addNewMilkItem(Categori categori, int ID , String name , double price , double fat , double liters) throws ExceptionItemAlreadyExists{
        ItemMilk milk = new ItemMilk(categori,ID ,name ,price ,fat , liters);
        inventoryDatabase.insert(milk);
    }
    public void removeItem(int ID) throws ExceptionItemNotFound{ // methode ne marche a revoire
        inventoryDatabase.remove(ID);
    }
    public void increaseItemQuantity(int ID , int quantity) throws ExceptionItemNotFound{
        inventoryDatabase.findByID(ID).increaseQuantityStock(quantity);
    }
    public void decreaseItemQuantity(int ID , int quantity){
        if (inventoryDatabase.findByID(ID) == null) {
            throw new ExceptionItemNotFound(ID);
        }
        if (inventoryDatabase.findByID(ID).getQuantityInStock() < quantity)
            throw new ExceptionInsufficientQuantityInStock(quantity);

        inventoryDatabase.findByID(ID).decreaseQuantityStock(quantity);
    }
    public Item getItem(int ID){
        try {
            return inventoryDatabase.findByID(ID);
        } catch (ExceptionItemNotFound e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return null; //Demander au prof
    }
    public Item[] getArrayOfItems(){
        return inventoryDatabase.getArrayList();
    }
}