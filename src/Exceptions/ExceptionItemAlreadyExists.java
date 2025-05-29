package Exceptions;

/**
 * Exception levée lorsqu'on tente d'ajouter un item qui existe déjà
 * dans la base de données.
 */
public class ExceptionItemAlreadyExists extends RuntimeException {

    /**
     * Crée une exception indiquant que l'item avec l'ID donné existe déjà.
     *
     * @param ID identifiant de l'item déjà présent
     */
    public ExceptionItemAlreadyExists(int ID) {
        super("L'item " + ID + " Existe deja dans la base de donnees");
    }
}
