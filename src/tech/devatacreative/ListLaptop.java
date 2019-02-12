package tech.devatacreative;

import com.mysql.jdbc.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ListLaptop extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable tblLaptop = new JTable(model);
    JTextField tfSearch;

    public ListLaptop(){
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Merk Laptop");
        model.addColumn("Tipe Laptop");
        model.addColumn("Jumlah Ready");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/toko", "root", "");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM laptop");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getString(2), Rs.getString(3),Rs.getString(4)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tblLaptop.setEnabled(false);
        JScrollPane pg = new JScrollPane(tblLaptop);
        cnt.add(pg);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame ListLaptopFrame = new ListLaptop();
        ListLaptopFrame.setTitle("List Laptop Ready");
        ListLaptopFrame.setVisible(true);
        ListLaptopFrame.setResizable(false);
        ListLaptopFrame.setSize(500, 300);
        ListLaptopFrame.setLocationRelativeTo(null);
        ListLaptopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}



