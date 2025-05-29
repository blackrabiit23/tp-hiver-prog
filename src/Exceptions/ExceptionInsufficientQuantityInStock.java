package Exceptions;

/**
 * Exception levée lorsque la quantité en stock est insuffisante
 * pour effectuer un retrait.
 */
public class ExceptionInsufficientQuantityInStock extends RuntimeException {

    /**
     * Crée une exception indiquant que le stock est insuffisant.
     *
     * @param quantite la quantité demandée à retirer
     */
    public ExceptionInsufficientQuantityInStock(int quantite) {
        super("Stock insuffisant pour enlever " + quantite + " unite(s)");
    }
}
