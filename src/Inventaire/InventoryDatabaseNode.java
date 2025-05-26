package Inventaire;
import Item.Item;

public class InventoryDatabaseNode {
    Item item;
    InventoryDatabaseNode next;

    public InventoryDatabaseNode(Item item){
        this.item = item;
    }
}
