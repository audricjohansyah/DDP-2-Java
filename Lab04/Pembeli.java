class Pembeli {

    private String nama;
    private long jumlahUang;
    private Pesanan[] listPesanan = new Pesanan[20];
    private int kapasitasBarang = listPesanan.length;

    public Pembeli(String nama, long jumlahUang){
        this.nama = nama;
        this.jumlahUang = jumlahUang;
    }

    // method untuk mencetak pesanan
    public void cetakPesanan(Pembeli pembeli){
        Pesanan[] listPesananUser = pembeli.getListPesanan();
        long totalHarga = 0;
        long jumlahBarang = 0;

        // mencetak pesanan dari list pesanan pembeli
        for (int i = 0; i < listPesananUser.length; i++){
            if(listPesananUser[i] != null){
                long harga = listPesananUser[i].getBarang().getHarga();
                int jumlah = listPesananUser[i].getJumlahBarang();
                String nama = listPesananUser[i].getBarang().getNama();
                totalHarga += harga*jumlah;
                jumlahBarang += jumlah;
                System.out.println(nama + ": " + harga + " * " + jumlah + " = " + harga*jumlah);
            }
        }
        
        // menghitung diskon, harga bayar, dan set sisa uang
        long diskon = (jumlahBarang*totalHarga) / 100;
        long hargaBayar = totalHarga-diskon;
        long sisaUang = this.getJumlahUang() + diskon;
        this.setJumlahUang(sisaUang);
        pembeli.resetPesanan();

        // mencetak detail pembayaran
        System.out.println("_____________________________________");
        System.out.println("Total harga = " + totalHarga);
        System.out.println("Diskon = " + diskon);
        System.out.println("Harga bayar = " + hargaBayar);
        System.out.println("Sisa uang = " + this.getJumlahUang());
        System.out.println("#######################################");
    }

    /*
     * Method untuk mengosongkan list pesanan
     */
    public void resetPesanan(){
        Pesanan[] arrPesanan = new Pesanan[20];
        listPesanan = arrPesanan;
        kapasitasBarang = listPesanan.length;
    }

    // method getter dan setter
    public String getNama() {
        return this.nama;
    }

    public long getJumlahUang(){
        return this.jumlahUang;
    }

    public void setJumlahUang(long jumlahUang){
        this.jumlahUang = jumlahUang;
    }

    public Pesanan[] getListPesanan(){
        return this.listPesanan;
    }

    // method untuk mengecek apabila user sudah melewati batas kapasitas pembelian
    public boolean checkMaxBarang(int jumlah){
        boolean maxCapacity = false;
        if (jumlah > kapasitasBarang){
            maxCapacity = true;
        }
        else{
            kapasitasBarang -= jumlah;
        }
        return maxCapacity;
    }

    // method untuk menambahkan pesanan ke list pesanan
    public void addToListPesanan(Barang barang, int jumlah){
        for (int i = 0; i < listPesanan.length; i++){
            if (listPesanan[i] == (null)){
                listPesanan[i] = new Pesanan(barang, jumlah); // memasukkan barang ke list pesanan
                break;
            }
            else if (listPesanan[i].getBarang().getNama().equals(barang.getNama())){
                listPesanan[i].setJumlahBarang(listPesanan[i].getJumlahBarang()+jumlah); // menambahkan barang jika jenis yang dipesan sama
                break;
            }
        }
    }
}