package tech.devatacreative;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    DefaultTableModel modelTabelPenjualan;
    Locale locale = new Locale("in", "ID");
    Currency currency = Currency.getInstance(locale);
    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();




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
        String Kasir;

    }

    public void setLabelTotalPrice() {
        Integer jml, total;
        Integer totalHarga;
        totalHarga = 0;
        for (int i = 0; i < tblListPenjualan.getRowCount(); i++) {
            jml = (Integer) tblListPenjualan.getValueAt(i,3);
            total = (Integer) tblListPenjualan.getValueAt(i,4)*jml;
            totalHarga += total;
            System.out.println(totalHarga);
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
