/**
 * Classe pour cree des noeud
 *
 * @author El khazraji Brahim
 * @version 1.0
 */
package Inventaire;
import Item.Item;

public class InventoryDatabaseNode {
    Item item;
    InventoryDatabaseNode next;

    /**
     * Constructeur du noeud
     *
     * @param item item a insere
     */
    public InventoryDatabaseNode(Item item){
        this.item = item;
    }
}
