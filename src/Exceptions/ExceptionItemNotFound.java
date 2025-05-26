package Exceptions;

public class ExceptionItemNotFound extends RuntimeException {
    public ExceptionItemNotFound(int ID) {
        super("L'item "+ID+" n'existe pas dans la base de donnees");
    }
}
