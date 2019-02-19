package tech.devatacreative;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

public class POS extends MainInventory {
    JTextField tfTanggalTransaksi;
    JTable tblListPenjualan;
    JButton btnTambahBarang;
    JButton btnHapusBarang;
    JTextField tfJumlahBayar;
    JButton btnBayar;
    JLabel labelJudul;
    JPanel panelPenjualan;
    JLabel labelTotalPrice;
    JComboBox cbKasir;
    JTextField tfQty;
    JTextField tfKodeBarang;
    private JButton btnTransaksiBaru;
    DefaultTableModel modelTabelPenjualan;
    Locale locale = new Locale("in", "ID");
    Currency currency = Currency.getInstance(locale);
    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();
    Integer totalhargatf;




    public POS(){

        modelTabelPenjualan = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTabelPenjualan.addColumn("Kode Barang");
        modelTabelPenjualan.addColumn("Merk");
        modelTabelPenjualan.addColumn("Tipe");
        modelTabelPenjualan.addColumn("Jumlah");
        modelTabelPenjualan.addColumn("Harga");
        tblListPenjualan.setModel(modelTabelPenjualan);

        btnBayar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfJumlahBayar.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Field kosong !");
                } else {
                    bayar();
                }
            }
        });
        btnTambahBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnTambahBarang();

            }
        });
        //set Tanggal
        tfTanggalTransaksi.setText(date.toString());
        tfTanggalTransaksi.setEnabled(false);

        //setKasir
        setCbKasir();

        btnHapusBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnHapusBarang();
                if (tblListPenjualan.getModel().getRowCount() == 0){
                    labelTotalPrice.setText(currency.getSymbol(locale)+" "+"0");
                }

            }
        });
        btnTransaksiBaru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTransaction();
            }
        });
    }

    public void setCbKasir(){

        try {
            String sql = "SELECT * FROM users";
            makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            while (result.next()){
                cbKasir.addItem(result.getString(2));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void bayar(){
        String Kasir,sql, messangePopUp;
        String totalHargaBayar;
        Integer  kodebarang, jmlBarang, jmlBayar, kembalian, totalhargahitung;
        Object objjmlbarang, objKodeBarang;


        makeConnection();
        try {
            Kasir = (String)cbKasir.getSelectedItem();
            totalHargaBayar = labelTotalPrice.getText().split("Rp ").toString();

//            for (int i = 0; i < tblListPenjualan.getRowCount(); i++){
            objjmlbarang = tblListPenjualan.getValueAt(0,3).toString();
            objKodeBarang = tblListPenjualan.getValueAt(0,0).toString();
            jmlBarang = Integer.parseInt((String) objjmlbarang);
            kodebarang = Integer.parseInt((String) objKodeBarang);
//            }
            sql = "INSERT INTO penjualan(id_barang, kasir, jumlah_barang, total_harga, tanggal_pembelian) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kodebarang);
            preparedStatement.setString(2, Kasir);
            preparedStatement.setInt(3,jmlBarang);
            preparedStatement.setInt(4, totalhargatf);
            preparedStatement.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.executeUpdate();
            jmlBayar = Integer.parseInt(tfJumlahBayar.getText());
            kembalian = jmlBayar - totalhargatf;
            messangePopUp = "Kembalian anda : "+ kembalian;
            if (jmlBayar > totalhargatf){
                JOptionPane.showMessageDialog(null, messangePopUp);
            } else if (jmlBayar < totalhargatf){
                JOptionPane.showMessageDialog(null, "Jumlah Uang Kurang");
            }
            decreaseStockReady();
            JOptionPane.showMessageDialog(null, "Pembayaran Berhasil !");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void decreaseStockReady(){
        String sql;
        Integer kodeBarang, jmlBarang;
        Object objKodeBarang, objjmlbarang;
        objjmlbarang = tblListPenjualan.getValueAt(0,3).toString();
        objKodeBarang = tblListPenjualan.getValueAt(0,0).toString();
        jmlBarang = Integer.parseInt((String) objjmlbarang);
        kodeBarang = Integer.parseInt((String) objKodeBarang);

        makeConnection();
        try {
            sql = "UPDATE laptop SET ready = ready - ? WHERE id_barang = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,jmlBarang);
            preparedStatement.setInt(2, kodeBarang);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void newTransaction(){
        modelTabelPenjualan.setRowCount(0);
        labelTotalPrice.setText(currency.getSymbol(locale)+" "+"0");
        tfKodeBarang.setText("");
        tfQty.setText("");
        tfJumlahBayar.setText("");
    }

    public void setLabelTotalPrice() {
        Integer jml, total;
        Integer totalHarga;
        totalHarga = 0;
        for (int i = 0; i < tblListPenjualan.getRowCount(); i++) {
            jml = (Integer) tblListPenjualan.getValueAt(i,3);
            total = (Integer) tblListPenjualan.getValueAt(i,4)*jml;
            totalHarga += total;
            totalhargatf = totalHarga;
            System.out.println(totalHarga);
            labelTotalPrice.setFont(new Font(labelTotalPrice.getName(), Font.BOLD, 30));
            labelTotalPrice.setText(currency.getSymbol(locale)+" "+String.format("%,d",totalHarga));
        }
    }

    public void setBtnHapusBarang() {
        Integer row = tblListPenjualan.getSelectedRow();
        if (row != -1){
            modelTabelPenjualan.removeRow(row);
            setLabelTotalPrice();
        }

    }

    public void setBtnTambahBarang(){
       String a,b,F, c, sql;
       Integer idBarang, qty,d,f;
       idBarang = Integer.parseInt(tfKodeBarang.getText());
       qty = Integer.parseInt(tfQty.getText());

       try{
           sql = "SELECT * FROM laptop WHERE id_barang=?";
           makeConnection();
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setInt(1,idBarang);
           result = preparedStatement.executeQuery();
           while (result.next()){
               a = result.getString(1);
               b = result.getString(2);
               c = result.getString(3);
               d = qty;
               f = result.getInt(5);
//               F = currency.getSymbol(locale)+" "+String.format("%,d",f);
               Object[] obj = new Object[]{a,b,c,d,f};
               modelTabelPenjualan.addRow(obj);
           }
           tblListPenjualan.setModel(modelTabelPenjualan);
       } catch (SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       setLabelTotalPrice();
    }



    public static void main(String[] args) {
        JFrame updateFrame = new JFrame("Point-of-Sale Laptop");
        updateFrame.setContentPane(new POS().panelPenjualan);
        updateFrame.pack();
        updateFrame.setVisible(true);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setResizable(false);

    }


}
