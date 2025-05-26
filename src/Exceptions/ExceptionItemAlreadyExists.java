package Exceptions;

public class ExceptionItemAlreadyExists extends RuntimeException {
    public ExceptionItemAlreadyExists(int ID) {
        super("L'item "+ID+" Existe deja dans la base de donnees");
    }
}
