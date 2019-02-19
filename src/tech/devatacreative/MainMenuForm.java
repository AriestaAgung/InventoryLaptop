package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm extends MainInventory {
     JPanel panelMainMenu;
    private JButton inventoryButton;
    private JButton pointOfSaleButton;


    public MainMenuForm() {
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilMainInventoryForm();
            }
        });
        pointOfSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilPOS();
            }
        });
    }

    public static void tampilPOS(){
        JFrame posFrame = new JFrame("Point-of-sale Laptop");
        posFrame.setContentPane(new POS().panelPenjualan);
        posFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        posFrame.pack();
        posFrame.setVisible(true);
        posFrame.setResizable(false);
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Inventory Laptop");
        mainFrame.setContentPane(new MainMenuForm().panelMainMenu);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}
