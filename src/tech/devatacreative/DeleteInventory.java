package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteInventory extends MainInventory {
    public JPanel panelDelete;
    private JTextField tfID;
    private JButton btnDelete;
    Integer id;
    String sql;

    public DeleteInventory() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
    }

    public void deleteData(){
       id = Integer.parseInt(tfID.getText());
       makeConnection();

       if (!id.equals("")) {
           try {
               sql = "DELETE FROM laptop WHERE id =?";
               preparedStatement = connection.prepareStatement(sql);
               preparedStatement.setInt(1, id);
               preparedStatement.executeUpdate();
               preparedStatement.close();
               JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus !");
           } catch (SQLException e) {
               Logger.getLogger(DeleteInventory.class.getName()).log(Level.SEVERE, null, e);
               JOptionPane.showMessageDialog(null, "Data tidak berhasil dihapus !");
           }
       } else {
           JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id To Delete");
       }

    }

    public static void main(String[] args) {
        JFrame updateFrame = new JFrame("Inventory Laptop - Delete");
        updateFrame.setContentPane(new DeleteInventory().panelDelete);
        updateFrame.pack();
        updateFrame.setVisible(true);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setResizable(false);
    }
}
