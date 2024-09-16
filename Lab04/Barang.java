// import java.util.Objects;

class Barang {
    private long harga;
    private String nama;
    private int stok;

    public Barang(long harga, String nama, int stok){
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public long getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public String getNama() {
        return nama;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
