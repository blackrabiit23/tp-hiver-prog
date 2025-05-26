package Item;


public class ItemBread extends Item{

    private String color;
    private double weight;

    public ItemBread(Categori category,int ID , String name ,double price, String color , int weight){
        super(category,ID,name,price);
        this.color = color;
        this.weight = weight;
        this.categori =category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Categori getCategory(){ // A revoire
        return Categori.Bread;
    }
    @Override
    public String infoToString(){
        return super.infoToString()+
                "Color : "+color+"\n"
                +"Weight :"+ weight;
    }
    public String getCategoryString(){
        return "Bread";
    }
}