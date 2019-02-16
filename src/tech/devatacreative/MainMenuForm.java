package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm extends MainInventory {
    private JPanel panelMainMenu;
    private JButton inventoryButton;
    private JButton pointOfSaleButton;


    public MainMenuForm() {
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilMainForm();
            }
        });
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
