package tech.devatacreative;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListTableLaptop extends MainForm {
     JPanel panelListTableLaptop;
     JRadioButton rbMerk;
     JRadioButton rbTipe;
     JRadioButton rbReady;
     JTextField tfSearch;
    DefaultTableModel model = new DefaultTableModel();
    private static JFrame LaptopListSearch = new JFrame("List Laptop");
    private JTable tblListLaptop = new JTable(model);
    private JButton btnSearch;


    public ListTableLaptop(){
        model.addColumn("no");
        model.addColumn("Merk Laptop");
        model.addColumn("Tipe Laptop");
        model.addColumn("Jumlah Ready");

        try {
            makeConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM laptop");
            result = preparedStatement.executeQuery();
            tblListLaptop.setModel(DbUtils.resultSetToTableModel(result));
            while (result.next()){
                Integer i = 0;
                i++;
                model.addRow(new Object[]{i.toString(), result.getString(2), result.getString(3), result.getString(4)});
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ArrayList<Laptop> laptopList(){
        ArrayList<Laptop> laptops = new ArrayList<>();
        String sql;

        try{
            makeConnection();
            sql = "SELECT * FROM laptop";
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            Laptop laptop;
            while (result.next()){
                laptop = new Laptop(result.getString(2), result.getString(3), result.getInt(1), result.getInt(4));
                laptops.add(laptop);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
            return laptops;
    }

    public void showLaptop(){
        ArrayList<Laptop> listLaptop = laptopList();

    }

    public static void main(String[] args) {

        LaptopListSearch.setTitle("List Laptop Ready");
        LaptopListSearch.setContentPane(new ListTableLaptop().panelListTableLaptop);
        LaptopListSearch.pack();
        LaptopListSearch.setVisible(true);
        LaptopListSearch.setResizable(false);
        LaptopListSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
