package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import Exceptions.ExceptionInsufficientQuantityInStock;
import Exceptions.ExceptionItemAlreadyExists;
import Exceptions.ExceptionItemNotFound;
import Inventaire.*;
import Item.*;

public class GUIInventoryManager extends JFrame
{
    private InventoryManager inventoryManager;
    private DefaultListModel<Item> itemsListModel;
    private JList itemsList;
    private int nextID;

    public GUIInventoryManager(InventoryManager inventoryManager) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.inventoryManager = inventoryManager;
        nextID = 100;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        add(createTitlePane(), BorderLayout.NORTH);
        add(createContentPane(), BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
    }

    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(border());
        contentPane.setLayout(new BorderLayout());

        contentPane.add(createItemActions(), BorderLayout.NORTH);
        contentPane.add(createItemsList(), BorderLayout.CENTER);
        contentPane.add(createNewButton(), BorderLayout.SOUTH);

        return contentPane;
    }

    private JPanel createTitlePane() {
        JPanel titlePane = new JPanel();
        titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.Y_AXIS));
        titlePane.setBorder(border());

        JLabel title = new JLabel("Gestionnaire d'inventaire", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(titleBorder());
        titlePane.add(title);
        titlePane.add(new JSeparator());

        return titlePane;
    }

    private JScrollPane createItemsList()
    {
        itemsListModel = new DefaultListModel<>();

        for (Item item : inventoryManager.getArrayOfItems()) {
            itemsListModel.addElement(item);
        }

        itemsList = new JList(itemsListModel);
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(itemsList);

        return listScroller;
    }

    private JPanel createItemActions() {
        JPanel itemButtons = new JPanel();
        itemButtons.setLayout(new BoxLayout(itemButtons, BoxLayout.X_AXIS));

        itemButtons.add(createViewButton());
        itemButtons.add(createIncreaseButton());
        itemButtons.add(createDecreaseButton());
        itemButtons.add(createEditButton());
        itemButtons.add(createDeleteButton());

        return itemButtons;
    }

    private JButton createViewButton() {
        JButton button = new JButton(new ImageIcon("icons/view.png"));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                try {
                    GUIItemDialog dialog = new GUIItemDialog(this, item, false);
                    dialog.setVisible(true);
                }catch (ExceptionItemNotFound e){
                    System.err.println("Erreur : "+e.getMessage());
                }
            }
        });

        return button;
    }

    private JButton createIncreaseButton() {
        JButton button = new JButton(new ImageIcon("icons/increase.png"));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                try {
                    item.increaseQuantityStock(1);
                    inventoryManager.getItem(item.getID()).increaseQuantityStock(1);
                }catch (ExceptionItemNotFound e){
                    GUIErrorDialog erreur = new GUIErrorDialog(this, e.getMessage());
                    erreur.setVisible(true);
                    System.err.println("Erreur :"+e.getMessage());

                }
            }
        });

        return button;
    }

    private JButton createDecreaseButton() {
        JButton button = new JButton(new ImageIcon("icons/decrease.png"));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                try {
                    item.decreaseQuantityStock(1);
                    inventoryManager.getItem(item.getID()).decreaseQuantityStock(1);
                } catch (ExceptionInsufficientQuantityInStock e) {
                    GUIErrorDialog erreur = new GUIErrorDialog(this, e.getMessage());
                    erreur.setVisible(true);
                    System.err.println("Erreur : "+e.getMessage());
                }
            }
        });

        return button;
    }

    private JButton createEditButton() {
        JButton button = new JButton(new ImageIcon("icons/edit.png"));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item) itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            } else {
                GUIItemDialog dialog = new GUIItemDialog(this , item , true);
                dialog.setVisible(true);
                try {
                    inventoryManager.getItem(item.getID()).setName(item.getName());
                    inventoryManager.getItem(item.getID()).setPrice(item.getPrice());
                    inventoryManager.getItem(item.getID()).setQuantityInStock(item.getQuantityInStock());
                    if (item instanceof ItemMilk) {
                        ItemMilk milk = (ItemMilk) inventoryManager.getItem(item.getID());
                        milk.setFat(((ItemMilk) item).getFat());
                        milk.setLiters(((ItemMilk) item).getLiters());
                    } else if (item instanceof ItemBread) {
                        ItemBread bread = (ItemBread) inventoryManager.getItem(item.getID());
                        bread.setWeight(((ItemBread) item).getWeight());
                        bread.setColor(((ItemBread) item).getColor());
                    } else if (item instanceof ItemEggs) {
                        ItemEggs eggs = (ItemEggs) inventoryManager.getItem(item.getID());
                        eggs.setNumber(((ItemEggs) item).getNumber());
                        eggs.setColor(((ItemEggs) item).getColor());
                    }
                }catch (ExceptionItemNotFound e){
                    System.err.println("Erreur : "+e.getMessage());
                }
            }
        });

        return button;
    }

    private JButton createDeleteButton() {
        JButton button = new JButton(new ImageIcon("icons/delete.png"));
        button.setBorder(buttonBorder());

        button.addActionListener(event -> {
            Item item = (Item)itemsList.getSelectedValue();
            if (item == null) {
                showSelectErrorDialog();
            }
            else {
                try {
                    this.itemsListModel.removeElement(item);
                    inventoryManager.removeItem(item.getID());
                    System.out.println("Suppresion reussi de l'item "+ item.getID());
                }
                catch (ExceptionItemNotFound e) {
                    showErrorDialog("L'item n'existe pas");
                    System.err.println("Erreur : " + e.getMessage());
                }
            }
        });

        return button;
    }

    private JPanel createNewButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton newItemButton = new JButton(new ImageIcon("icons/new.png"));
        newItemButton.addActionListener(event -> {
            GUIItemChoiceDialog guiItemChoiceDialog = new GUIItemChoiceDialog(this);

            guiItemChoiceDialog.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent e) { }

                @Override
                public void componentMoved(ComponentEvent e) { }

                @Override
                public void componentShown(ComponentEvent e) { }

                @Override
                public void componentHidden(ComponentEvent e) {
                    Categori categori = guiItemChoiceDialog.getChosenCategory();
                    try {
                        if (categori == Categori.Bread) {
                            ItemBread bread = new ItemBread(Categori.Bread, 100, "nouvelle item a modifier", 0, "couleur a modifier", 0);
                            itemsListModel.addElement(bread);
                            inventoryManager.addNewBreadItem(Categori.Bread, 100, "nouvelle item a modifier", 0, "couleur a modifier", 0);
                        }
                        if (categori == Categori.Eggs) {
                            ItemEggs egg= new ItemEggs(Categori.Eggs, 100, "nouvelle item a modifier", 0, "couleur a modifier", 0);
                            itemsListModel.addElement(egg);
                            inventoryManager.addNewEggsItem(Categori.Eggs, 100, "nouvelle item a modifier", 0, "couleur a modifier", 0);
                        }
                        if (categori == Categori.Milk){
                            ItemMilk milk= new ItemMilk(Categori.Milk, 100, "nouvelle item a modifier", 0, 0, 0);
                            itemsListModel.addElement(milk);
                            inventoryManager.addNewEggsItem(Categori.Eggs, 100, "nouvelle item a modifier", 0, "couleur a modifier", 0);
                        }
                    }catch (ExceptionItemAlreadyExists g){
                        showErrorDialog(g.getMessage());
                    }
                }
            });

            guiItemChoiceDialog.setVisible(true);
        });

        newItemButton.setBorder(buttonNewBorder());
        buttonPanel.add(newItemButton);

        return buttonPanel;
    }

    private void showSelectErrorDialog() {

        showErrorDialog("SVP choisir un item");
    }

    private void showErrorDialog(String message) {
        GUIErrorDialog dialog = new GUIErrorDialog(this, message);
        dialog.setVisible(true);
    }

    private Border border() {

        return BorderFactory.createEmptyBorder(5, 10, 10, 10);
    }

    private Border titleBorder() {

        return BorderFactory.createEmptyBorder(5, 0, 10, 10);
    }

    private Border buttonNewBorder() {
        return BorderFactory.createEmptyBorder(5, 0, 0, 0);
    }

    private Border buttonBorder() {

        return BorderFactory.createEmptyBorder(0, 5, 10 , 5);
    }




}
