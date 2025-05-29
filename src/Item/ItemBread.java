/**
 * Classe de itemBred qui heritent de Item pour un item pain
 *
 * @author El khazraji Brahim
 * @version 1.0
 */
package Item;


public class ItemBread extends Item {

    private String color;
    private double weight;

    /**
     * Constructeur, pour cree des instances ItemBread.
     *
     * @param category La catégorie de l'article.
     * @param ID L'identifiant de l'article.
     * @param name Le nom de l'article.
     * @param price Le prix de l'article.
     * @param color La couleur du pain..
     * @param weight Le poid du pain
     */
    public ItemBread(Categori category, int ID, String name, double price, String color, int weight) {
        super(category, ID, name, price);
        this.color = color;
        this.weight = weight;
        this.categori = category;
    }

    /**
     * Obtient la couleur de l'objet.
     *
     * @return La couleur actuelle.
     */
    public String getColor() {
        return color;
    }

    /**
     * Définit la couleur de l'objet.
     *
     * @param color La couleur à attribuer.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtient le poids de l'objet.
     *
     * @return Le poids actuel.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Définit le poids de l'objet.
     *
     * @param weight Le poids à attribuer.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Obtient la catégorie de l'objet.
     *
     * @return La catégorie {@code Categori.Bread}.
     */
    public Categori getCategory() { // À revoir si la catégorie doit être dynamique
        return Categori.Bread;
    }

    /**
     * Retourne une représentation textuelle des informations de l'objet.
     *
     * @return Une chaîne de caractères contenant les informations de base
     * et les attributs color et weight.
     */
    @Override
    public String infoToString() {
        return super.infoToString() +
                "Color : " + color + "\n" +
                "Weight : " + weight;
    }

    /**
     * Retourne la catégorie de l'objet sous forme de chaîne.
     *
     * @return La chaîne "Bread".
     */
    public String getCategoryString() {
        return "Bread";
    }
}