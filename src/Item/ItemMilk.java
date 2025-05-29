/**
 * Classe itemMilk qui herite de Item pour un item lait.
 *
 * @author El khazraji Brahim
 * @version 1.0
 */
package Item;

public class ItemMilk extends Item{

    private double fat;
    private double liters;

    /**
     * Constructeur de ItemMilk
     *
     * @param category La catégorie de l'article.
     * @param ID L'identifiant de l'article.
     * @param name Le nom de l'article.
     * @param price Le prix de l'article.
     * @param fat Le gras du lait
     * @param liters Le nombre de liters de l'article.
     */
    public ItemMilk(Categori category,int ID , String name ,double price ,double fat ,double liters){
        super(category,ID,name,price);
        this.fat = fat;
        this.liters = liters;
    }

    /**
     * Methode qui retourne le gras du lait
     *
     * @return fat
     */

    public double getFat() {
        return fat;
    }

    /**
     * Methode qui retourne les litres du lait
     *
     * @return litres
     */
    public double getLiters() {
        return liters;
    }

    /**
     * Methode pour modifier les litres du lait
     *
     * @param liters
     */
    public void setLiters(double liters) {
        this.liters = liters;
    }

    /**
     * Methode pour modifier le gras du lait
     *
     * @param fat
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Methode qui retourne la Categorie de l'item
     *
     * @return Categorie.Milk
     */
    public Categori getCategory(){
        return Categori.Milk;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations détaillées de l'article.
     *
     * @return Une description complète de l'article.
     */
    @Override
    public String infoToString(){
        return super.infoToString()
            +"fat : "+fat +"\n" +
            "liters : "+liters;
    }

    /**
     * Retourne la catégorie de l'article sous forme de chaîne.
     *
     * @return La chaîne "Eggs".
     */
    public String getCategoryString(){
        return "Milk";
    }
}