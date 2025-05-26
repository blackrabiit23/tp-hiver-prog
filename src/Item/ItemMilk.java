package Item;

public class ItemMilk extends Item{

    private double fat;
    private double liters;

    public ItemMilk(Categori category,int ID , String name ,double price ,double fat ,double liters){
        super(category,ID,name,price);
        this.fat = fat;
        this.liters = liters;
    }

    public double getFat() {
        return fat;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public Categori getCategory(){
        return Categori.Milk;
    }
    @Override
    public String infoToString(){
        return super.infoToString()
            +"fat : "+fat +"\n" +
            "liters : "+liters;
    }
    public String getCategoryString(){
        return "Milk";
    }
}
