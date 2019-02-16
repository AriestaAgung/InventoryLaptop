package tech.devatacreative;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ListTableLaptop extends MainInventory {
     JPanel panelListTableLaptop;
     JRadioButton rbMerk;
     JRadioButton rbTipe;
     JRadioButton rbReady;
     JTextField tfSearch;
     DefaultTableModel model;
     static JFrame LaptopListSearch = new JFrame("List Laptop");
     JTable tblTableListLaptop;
     JButton btnSearch;
     JScrollPane jspTblScroll;
     JRadioButton rbHarga;
     ButtonGroup rbGroup = new ButtonGroup();
     Locale locale = new Locale("in", "ID");
     Currency currency = Currency.getInstance(locale);

    public void setRbGroup() {
        rbGroup.add(rbMerk);
        rbGroup.add(rbTipe);
        rbGroup.add(rbReady);
        rbGroup.add(rbHarga);
        rbMerk.setSelected(true);
    }

    public ListTableLaptop(){
         String a,b;
         Integer c,d;
        setRbGroup();
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Merk Laptop");
        model.addColumn("Tipe Laptop");
        model.addColumn("Jumlah Ready");
        model.addColumn("Harga");
        String sql = "SELECT * FROM laptop";
        try {
            makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            while (result.next()){
                 a = result.getString(2);
                 b = result.getString(3);
                 c = result.getInt(4);
                 d = result.getInt(5);
                 String D = currency.getSymbol(locale)+" "+String.format("%,d",d);
                 model.addRow(new Object[]{a,b,c,D});
            }
            tblTableListLaptop.setModel(model);
//            tblTableListLaptop;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tfSearch.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kotak Pencarian Kosong !");
                } else {
                    searchLaptop();
                }

            }
        });
    }

    private void searchLaptop(){
        String search;
        String a,b,D;
        Integer c,d;

        if (rbMerk.isSelected()){
            String sql = "SELECT * FROM laptop WHERE merk_laptop=?";
            search = tfSearch.getText();
            try {
                makeConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, search);
                result = preparedStatement.executeQuery();
                model.setRowCount(0);
                while (result.next()){
                    a = result.getString(2);
                    b = result.getString(3);
                    c = result.getInt(4);
                    d = result.getInt(5);
                    D = currency.getSymbol(locale)+" "+String.format("%,d",d);
                    model.addRow(new Object[]{a,b,c,D});
                }
                tblTableListLaptop.setModel(model);
//            tblTableListLaptop;
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (rbTipe.isSelected()){
            String sql = "SELECT * FROM laptop WHERE tipe_laptop=?";
            search = tfSearch.getText();
            try {
                makeConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, search);
                result = preparedStatement.executeQuery();
                model.setRowCount(0);
                while (result.next()){
                    a = result.getString(2);
                    b = result.getString(3);
                    c = result.getInt(4);
                    d = result.getInt(5);
                    D = currency.getSymbol(locale)+" "+String.format("%,d",d);
                    model.addRow(new Object[]{a,b,c,D});
                }
                tblTableListLaptop.setModel(model);
//            tblTableListLaptop;
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (rbReady.isSelected()){
            String sql = "SELECT * FROM laptop WHERE ready=?";
            search = tfSearch.getText();
            try {
                makeConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, search);
                result = preparedStatement.executeQuery();
                model.setRowCount(0);
                while (result.next()){
                    a = result.getString(2);
                    b = result.getString(3);
                    c = result.getInt(4);
                    d = result.getInt(5);
                    D = currency.getSymbol(locale)+" "+String.format("%,d",d);
                    model.addRow(new Object[]{a,b,c,D});
                }
                tblTableListLaptop.setModel(model);
//            tblTableListLaptop;
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (rbHarga.isSelected()){
            String sql = "SELECT * FROM laptop WHERE harga=?";
            search = tfSearch.getText();
            try {
                makeConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, search);
                result = preparedStatement.executeQuery();
                model.setRowCount(0);
                while (result.next()){
                    a = result.getString(2);
                    b = result.getString(3);
                    c = result.getInt(4);
                    d = result.getInt(5);
                    D = currency.getSymbol(locale)+" "+String.format("%,d",d);
                    model.addRow(new Object[]{a,b,c,D});
                }
                tblTableListLaptop.setModel(model);
//            tblTableListLaptop;
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        LaptopListSearch.setTitle("List Laptop Ready ");
        LaptopListSearch.setContentPane(new ListTableLaptop().panelListTableLaptop);
        LaptopListSearch.pack();
        LaptopListSearch.setVisible(true);
        LaptopListSearch.setResizable(false);
        LaptopListSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
