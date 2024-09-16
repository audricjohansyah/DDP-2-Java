class Pesanan {

    private Barang barang;
    private int jumlahBarang;

    public Pesanan(Barang barang, int jumlahBarang){
        this.barang = barang;
        this.jumlahBarang = jumlahBarang;
    }

    /*
     * Method yang mengembalikan total harga dari suatu pesanan
     */
    public long totalHarga(){
        return barang.getHarga() * jumlahBarang;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }
}
