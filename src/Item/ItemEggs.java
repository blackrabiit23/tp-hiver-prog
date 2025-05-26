package Item;

public class ItemEggs extends Item{

    private String color;
    private int number;

    public ItemEggs(Categori category,int ID , String name ,double price, String color , int number){
        super(category,ID,name,price);
        this.color = color;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categori getCategory(){
        return Categori.Eggs;
    }
    @Override
    public String infoToString(){
        return super.infoToString()
                +"Color : "+ color +"\n"
                +"Number : "+number+"\n";
    }
    public String getCategoryString(){
        return "Eggs";
    }
}
