package Inventaire;
import Exceptions.ExceptionItemAlreadyExists;
import Exceptions.ExceptionItemNotFound;
import Item.Item;

public class InventoryDatabase {
    private int ItemsCont;
    private InventoryDatabaseNode first;

    public InventoryDatabase() {
        first = null;
    }

    public void insert(Item item) throws ExceptionItemAlreadyExists {
        InventoryDatabaseNode courant = first;
        while (courant != null) {
            if (courant.item.getID() == item.getID())
                throw new ExceptionItemAlreadyExists(item.getID());
            courant = courant.next;
        }
        InventoryDatabaseNode nouveau = new InventoryDatabaseNode(item);
        if (ItemsCont == 0) {
            first = nouveau;
            ItemsCont++;
            return;
        } else {
            courant = first;
            while (courant.next != null) {
                courant = courant.next;
            }
            courant.next = nouveau;
        }
        ItemsCont++;
    }

    public Item findByID(int ID) throws ExceptionItemNotFound {
        if (ItemsCont == 0)
            System.out.println("La liste est vide");

        InventoryDatabaseNode courant = first;

        while (courant != null) {
            if (courant.item.getID() == ID) {
                return courant.item;
            }
            courant = courant.next;
        }
        throw new ExceptionItemNotFound(ID);
    }

    public void remove(int ID) throws ExceptionItemNotFound {
        if (first == null)
            throw new ExceptionItemNotFound(ID);
        if (first.item.getID() == ID) {
            first = first.next;
            ItemsCont--;
            return;
        }

        InventoryDatabaseNode precedant = first;
        InventoryDatabaseNode courant = first.next;

        while (courant != null) {
            if (courant.item.getID() == ID) {
                precedant.next = courant.next;
                ItemsCont--;
                return;
            }
            precedant = courant;
            courant = courant.next;
        }
        throw new ExceptionItemNotFound(ID);
    }

    public Item[] getArrayList() {
        Item[] liste = new Item[ItemsCont];
        InventoryDatabaseNode courant = first;
        for (int i = 0; i < ItemsCont; i++) {
            liste[i] = courant.item;
            if(courant.next != null) {
                courant = courant.next;
            }
        }
        return liste;
    }
}
