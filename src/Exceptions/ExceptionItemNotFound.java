package Exceptions;

/**
 * Exception levée lorsqu'un item recherché n'existe pas
 * dans la base de données.
 */
public class ExceptionItemNotFound extends RuntimeException {

    /**
     * Crée une exception indiquant que l'item avec l'ID donné
     * est introuvable.
     *
     * @param ID identifiant de l'item recherché
     */
    public ExceptionItemNotFound(int ID) {
        super("L'item " + ID + " n'existe pas dans la base de donnees");
    }
}
