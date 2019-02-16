package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInventory extends MakeConnection{
    private JTextField tfMerkLaptop;
    private JTextField tfTipeLaptop;
    private JTextField tfJmlReady;
    private JButton btnSubmitData;
    private JButton btnUpdateData;
    private JButton btnDeleteData;
    private JPanel panelMain;
    private JButton btnTableLaptop;
    private JTextField tfHargaLaptop;


    public MainInventory() {

        btnSubmitData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfTipeLaptop == null || tfJmlReady == null || tfMerkLaptop == null){
                    JOptionPane.showMessageDialog(null, "Pastikan semua data terisi !");
                } else {
                    insertData();

                }
            }
        });
        btnUpdateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnUpdateData();
            }
        });
        btnDeleteData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnDeleteData();
            }
        });
        btnTableLaptop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnTableLaptop();
            }
        });
    }

    public void insertData(){
        String varMerk, varTipe, sql;
        Integer varHarga,varJml;
        makeConnection();

        try {
            varMerk = tfMerkLaptop.getText();
            varTipe = tfTipeLaptop.getText();
            varJml = Integer.parseInt(tfJmlReady.getText());
            varHarga = Integer.parseInt(tfHargaLaptop.getText());
            if (varJml.equals("") || varMerk.equals("") || varTipe.equals("") || varHarga.equals("")){
                JOptionPane.showMessageDialog(null, "Input Data Gagal !");
            } else {
                sql = "INSERT INTO laptop(merk_laptop, tipe_laptop, ready, harga) VALUES(?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, varMerk);
                preparedStatement.setString(2, varTipe);
                preparedStatement.setInt(3, varJml);
                preparedStatement.setInt(4, varHarga);
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Input Data Sukses !");
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void setBtnUpdateData(){
       JFrame updateFrame = new JFrame("Inventory Laptop - Update");
       updateFrame.setContentPane(new UpdateInventory().panelUpdate);
       updateFrame.pack();
       updateFrame.setVisible(true);
       updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       updateFrame.setResizable(false);
    }
    public void setBtnTableLaptop(){
        JFrame ListLaptopFrame = new JFrame();
        ListLaptopFrame.setTitle("List Laptop Ready");
        ListLaptopFrame.setContentPane(new ListTableLaptop().panelListTableLaptop);
        ListLaptopFrame.pack();
        ListLaptopFrame.setVisible(true);
        ListLaptopFrame.setResizable(false);
        ListLaptopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void setBtnDeleteData(){
        JFrame updateFrame = new JFrame("Inventory Laptop - Delete");
        updateFrame.setContentPane(new DeleteInventory().panelDelete);
        updateFrame.pack();
        updateFrame.setVisible(true);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setResizable(false);
    }
    public static void tampilMainForm(){
        JFrame mainFrame = new JFrame("Inventory Laptop");
        mainFrame.setContentPane(new MainInventory().panelMain);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

//    public static DefaultTableModel tableModel(result) throws SQLException {
//        ResultSetMetaData metaData = result.getMetaData();
//
//        Vector<String> columnNames = new Vector<String>();
//        int columnCount = metaData.getColumnCount();
//        for (int column = 1; column <= columnCount; column++){
//            columnNames.add(metaData.getColumnName(column));
//        }
//
//        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
//        while (result.next()){
//            Vector<Object>vector = new Vector<Object>();
//            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
//                vector.add(result.getObject(columnIndex));
//            }
//            data.add(vector);
//        }
//        return new DefaultTableModel(data, columnNames);
//    }

//    public void showTableData(){
//        ArrayList<Laptop> listLaptop =  getLaptopList();
//        DefaultTableModel model = (DefaultTableModel)tblDataLaptop.getModel();
//
//        model.setRowCount(0);
//
//        Object[] row = new Object[3];
//
//        for (int i = 0; i < listLaptop.size(); i++){
//            row[0] = listLaptop.get(i).getMerkLaptop();
//            row[1] = listLaptop.get(i).getTipeLaptop();
//            row[2] = listLaptop.get(i).getJmlReady();
////            row[3] = listLaptop.get(i).getAddDate();
//            model.addRow(row);
//        }
//
//    }
//
//    public ArrayList<Laptop> getLaptopList() {
//        ArrayList<Laptop> LaptopList = new ArrayList<Laptop>();
//
//        try {
//            String sql = "SELECT * FROM laptop";
//            statement = connection.createStatement();
//            result = statement.executeQuery(sql);
//            Laptop laptop;
//            while (result.next()) {
//                laptop = new Laptop(result.getString("merk_laptop"), result.getString("tipe_laptop"), result.getInt("id"), result.getInt("ready"));
//                LaptopList.add(laptop);
//            }
//
//        } catch (SQLException e) {
//            Logger.getLogger(MainInventory.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return LaptopList;
//    }



    public static void main(String[] args) {



//        tampilMainForm();
    }
}
