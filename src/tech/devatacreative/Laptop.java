package tech.devatacreative;

public class Laptop {

    private int id, jmlReady, harga;
    private String merkLaptop, tipeLaptop;

    public Laptop(String merk, String tipe, Integer id, Integer jml, Integer price){
        this.id = id;
        this.merkLaptop = merk;
        this.tipeLaptop = tipe;
        this.jmlReady = jml;
        this.harga = price;
    }

    public int getId() {
        return id;
    }

    public int getJmlReady() {
        return jmlReady;
    }

    public String getMerkLaptop() {
        return merkLaptop;
    }

    public String getTipeLaptop() {
        return tipeLaptop;
    }

    public int getHarga() {
        return harga;
    }
}
