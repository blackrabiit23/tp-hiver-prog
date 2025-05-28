/**
 * Classe utilitaire pour effectuer des calculs mathématiques simples.
 *
 * @author El khazraji Brahim
 * @version 1.0
 */
package Item;

import Exceptions.ExceptionInsufficientQuantityInStock;

public abstract class Item {
    private int ID;
    private String name;
    private double price;
    private int quantityStock;
    protected Categori categori;

    /**
     * Constructeure de item pour pouvoir initialiser des instance de Item.
     * @param categori
     * @param ID
     * @param name
     * @param price
     */
    public Item(Categori categori, int ID , String name , double price){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.categori = categori;
    }

    /**
     * Retourne l'identifiant de l'item
     *
     * @return Id de l'item
     */
    public int getID() {
        return ID;
    }

    /**
     * Modification de l'id de l'item
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Retourne le nom de l'item
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *Modification du nom de l'item
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le prix de l'item
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Modification du prix de l'item
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retourne la quantiter de l'item dans l'inventaire
     *
     * @return quantityStock
     */
    public int getQuantityInStock() {
        return quantityStock;
    }

    /**
     * Modifier la quantite de l'item dans l'inventaire
     *
     * @param quantityStock
     */
    public void setQuantityInStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    /**
     * Augmenter la quantite de l'item dans l'inventaire
     *
     * @param quantityStock
     */
    public void increaseQuantityStock(int quantityStock){
        this.quantityStock += quantityStock;
    }

    /**
     * Diminuer la quantite de l'item dans l'inventaire , Lance une exception si la quantiter est negatif
     *
     * @param quantityStock
     * @throws ExceptionInsufficientQuantityInStock
     */
    public void decreaseQuantityStock(int quantityStock) throws ExceptionInsufficientQuantityInStock{
        this.quantityStock -=quantityStock;
        if(this.quantityStock < 0 )
            throw new ExceptionInsufficientQuantityInStock(quantityStock);
    }

    /**
     * Retourne les information sur l'objet
     *
     * @return Les information sur l'item
     */
    public String infoToString(){
        return "Item information"+ "\n"
                +"ID :"+ID +"\n"
                +"Name :"+name+"\n"
                +"Price :"+price+"\n"
                +"In Stock: "+quantityStock+"\n";
    }
    /**
     * Retourne les information sur l'objet
     *
     * @return Les information sur l'item
     */
    @Override
    public String toString() {
        return ID+": "+getCategoryString()+" - "+name+"("+getQuantityInStock()+")";
    }

    /**
     * Classe abstraite , les objet qui heritent doivent obligatoirement l'implementer
     *
     */
    public abstract Categori getCategory();

    /**
     * Classe abstraite , les objet qui heritent doivent obligatoirement l'implementer
     *
     */
    public abstract String getCategoryString();
}