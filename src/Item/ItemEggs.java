package Item;

public class ItemEggs extends Item{

    private String color;
    private int number;
    /**
     * Constructeur de l'article œufs.
     *
     * @param category La catégorie de l'article.
     * @param ID L'identifiant de l'article.
     * @param name Le nom de l'article.
     * @param price Le prix de l'article.
     * @param color La couleur des œufs.
     * @param number Le nombre d'œufs.
     */
    public ItemEggs(Categori category, int ID, String name, double price, String color, int number) {
        super(category, ID, name, price);
        this.color = color;
        this.number = number;
    }

    /**
     * Retourne le nombre d'œufs.
     *
     * @return Le nombre d'œufs.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Définit le nombre d'œufs.
     *
     * @param number Le nouveau nombre d'œufs.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Retourne la couleur des œufs.
     *
     * @return La couleur.
     */
    public String getColor() {
        return color;
    }

    /**
     * Définit la couleur des œufs.
     *
     * @param color La nouvelle couleur.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Retourne la catégorie de l'article (fixée à {@code Categori.Eggs}).
     *
     * @return La catégorie {@code Categori.Eggs}.
     */
    public Categori getCategory() {
        return Categori.Eggs;
    }

    /**
     * Retourne une chaîne de caractères contenant les informations détaillées de l'article.
     *
     * @return Une description complète de l'article.
     */
    @Override
    public String infoToString() {
        return super.infoToString() +
                "Color : " + color + "\n" +
                "Number : " + number + "\n";
    }

    /**
     * Retourne la catégorie de l'article sous forme de chaîne.
     *
     * @return La chaîne "Eggs".
     */
    public String getCategoryString() {
        return "Eggs";
    }
}