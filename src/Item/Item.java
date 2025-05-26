package Item;

import Exceptions.ExceptionInsufficientQuantityInStock;

public abstract class Item {
    private int ID;
    private String name;
    private double price;
    private int quantityStock;
    protected Categori categori;

    public Item(Categori categori, int ID , String name , double price){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.categori = categori;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityStock;
    }

    public void setQuantityInStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }
    public void increaseQuantityStock(int quantityStock){
        this.quantityStock += quantityStock;
    }
    public void decreaseQuantityStock(int quantityStock) throws ExceptionInsufficientQuantityInStock{
        this.quantityStock -=quantityStock;
        if(this.quantityStock < 0 )
            throw new ExceptionInsufficientQuantityInStock(quantityStock);
    }
    public String infoToString(){
        return "Item information"+ "\n"
                +"ID :"+ID +"\n"
                +"Name :"+name+"\n"
                +"Price :"+price+"\n"
                +"In Stock: "+quantityStock+"\n";
    }

    @Override
    public String toString() {
        return "Item{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantityStock=" + quantityStock +
                '}';
    }

    public abstract Categori getCategory();
    public abstract String getCategoryString();
}
