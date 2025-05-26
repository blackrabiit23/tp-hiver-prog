package Exceptions;

public class ExceptionInsufficientQuantityInStock extends RuntimeException {
    public ExceptionInsufficientQuantityInStock(int quantite) {
        super("Stock insuffisant pour enlever pour enlever "+quantite+" unite(s)");
    }
}
