package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateInventory extends MainInventory {
    private String merkLaptop, tipeLaptop, sql;
    private Integer id, jmlReady, harga;
    private JTextField tfID;
    private JTextField tfMerk;
    private JTextField tfTipe;
    private JTextField tfjmlReady;
    private JButton btnUpdate;
    public JPanel panelUpdate;
    private JTextField tfharga;

    public UpdateInventory() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });
    }

    public void updateData(){

        makeConnection();


        try {
            id = Integer.parseInt(tfID.getText());
            merkLaptop = tfMerk.getText();
            tipeLaptop = tfTipe.getText();
            jmlReady = Integer.parseInt(tfjmlReady.getText());
            harga = Integer.parseInt(tfharga.getText());
            if (jmlReady.equals("") || merkLaptop.equals("") || tipeLaptop.equals("") || harga.equals("")) {
                JOptionPane.showMessageDialog(null, "Update Data Gagal !");
            } else {
                sql = "UPDATE laptop SET merk_laptop = ?, tipe_laptop = ?, ready = ?, harga = ? WHERE id_barang = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, merkLaptop);
                preparedStatement.setString(2, tipeLaptop);
                preparedStatement.setInt(3, jmlReady);
                preparedStatement.setInt(4, harga);
                preparedStatement.setInt(5, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                JOptionPane.showMessageDialog(null, "Update Data Sukses !");
            }
        }catch (SQLException e){
            Logger.getLogger(UpdateInventory.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Inventory Laptop - Update");
        mainFrame.setContentPane(new UpdateInventory().panelUpdate);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }
}
