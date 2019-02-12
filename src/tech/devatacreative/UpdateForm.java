package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateForm extends MainForm{
    private String merkLaptop, tipeLaptop, sql;
    private Integer id, jmlReady;
    private JTextField tfID;
    private JTextField tfMerk;
    private JTextField tfTipe;
    private JTextField tfjmlReady;
    private JButton btnUpdate;
    public JPanel panelUpdate;

    public UpdateForm() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });
    }

    public void updateData(){
        id = Integer.parseInt(tfID.getText());
        merkLaptop = tfMerk.getText();
        tipeLaptop = tfTipe.getText();
        jmlReady = Integer.parseInt(tfjmlReady.getText());
        makeConnection();


        try {
            sql = "UPDATE laptop SET merk_laptop = ?, tipe_laptop = ?, ready = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, merkLaptop);
            preparedStatement.setString(2, tipeLaptop);
            preparedStatement.setInt(3, jmlReady);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Update Data Sukses !");
        }catch (SQLException e){
            Logger.getLogger(UpdateForm.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Inventory Laptop - Update");
        mainFrame.setContentPane(new UpdateForm().panelUpdate);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }
}
