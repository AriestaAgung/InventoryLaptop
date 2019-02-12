package tech.devatacreative;

public class Laptop {

    private int id, jmlReady;
    private String merkLaptop, tipeLaptop;
    private Byte[] gambar;
    public Laptop(String merk, String tipe, Integer id, Integer jml){
        this.id = id;
        this.merkLaptop = merk;
        this.tipeLaptop = tipe;
        this.jmlReady = jml;

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

    public Byte[] getGambar() {
        return gambar;
    }
}
