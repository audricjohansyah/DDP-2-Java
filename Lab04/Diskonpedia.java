// import library yang dibutuhkan
import java.io.*;
import java.util.StringTokenizer;

public class Diskonpedia {

    // set up variable
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static Barang[] listBarang;
    private static Pembeli[] listPembeli;
  
    public static void main(String[] args) {

        // Inisiasi Barang
        int jumlahBarang = in.nextInt();
        listBarang = new Barang[jumlahBarang];

        // memasukkan objek barang baru ke list barang
        for(int i = 0; i < jumlahBarang; i++){
            String namaBarang = in.next();
            long harga = in.nextLong();
            int stok = in.nextInt();

            Barang barangBaru = new Barang(harga, namaBarang, stok);
            listBarang[i] = barangBaru;
        }
        // System.out.println(Arrays.toString(listBarang));

        // Inisiasi Pembeli
        int jumlahPembeli = in.nextInt();
        listPembeli = new Pembeli[jumlahPembeli];

        // memasukkan objek pembeli baru ke list pembeli
        for(int i = 0; i < jumlahPembeli; i++){
            String namaPembeli = in.next();
            long jumlahUang = in.nextLong();

            Pembeli pembeliBaru = new Pembeli(namaPembeli, jumlahUang);
            listPembeli[i] = pembeliBaru;
        }

        // Jalanin Query
        int jumlahPerintah = in.nextInt();
        for(int i = 0; i < jumlahPerintah; i++){
            String perintah = in.next();
            switch (perintah) {
                case "PESAN" -> {
                    String namaPembeli = in.next();
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    pesan(namaPembeli, namaBarang, jumlah);
                    break;
                }
                case "BAYAR" -> {
                    String namaPembeli = in.next();
                    bayar(namaPembeli);
                    break;
                }
                case "DISKON" -> {
                    String namaPembeli = in.next();
                    diskon(namaPembeli);
                    break;
                }
                case "RESTOCK" -> {
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    restock(namaBarang, jumlah);
                    break;
                }
            }
        }
        out.close();
    }

    /*
     * Method untuk perintah PESAN
     */
    public static void pesan(String namaPembeli, String namaBarang, int jumlah){
        // set up objek pembeli yang dibutuhkan
        Pembeli pembeli = cariPembeli(namaPembeli);
        long harga = 0;

        // memanggil method untuk mengecek apakah pembeli sudah melewati batas max beli
        if(pembeli.checkMaxBarang(jumlah)){
            if(jumlah > cariBarang(namaBarang).getStok()){
                System.out.println("Tidak bisa memesan " + namaBarang + " sebanyak " 
                +  jumlah +  " buah. Stok barang tidak cukup");
            }
            
            else{
                System.out.println("Tidak bisa memesan " + namaBarang + " sebanyak " + jumlah 
                +  " buah. List pesanan " + namaPembeli + " melebihi kapasitas");
            }
        }
        else{
            for (int i = 0; i < listBarang.length; i ++){
                if (namaBarang.equals(listBarang[i].getNama())){
                    if(listBarang[i].getStok() >= jumlah){
                        harga = jumlah*listBarang[i].getHarga();
                        if(pembeli.getJumlahUang() >= harga){
                            // mengurangi uang pembeli dan mengurangi stock barang jika pembeli mampu bayar 
                            pembeli.setJumlahUang(pembeli.getJumlahUang() - harga);
                            listBarang[i].setStok(listBarang[i].getStok() - jumlah);
                            
                            System.out.println(namaPembeli + " berhasil memesan " 
                            + namaBarang + " sebanyak " + jumlah + " buah");
                            
                            pembeli.addToListPesanan(listBarang[i], jumlah); // memasukkan pesanan pembeli ke list pesanan
                            break;
                        }
                        else{
                            System.out.println("Tidak bisa memesan "+  namaBarang + " sebanyak " 
                            + jumlah + " buah. "+ "Uang " + namaPembeli + " tidak cukup");
                        }
                    }
                    else{
                        System.out.println("Tidak bisa memesan " + namaBarang + " sebanyak " 
                        +  jumlah +  " buah. Stok barang tidak cukup");
                    }
                }
            }
        }
    }

    /*
     * Method untuk perintah BAYAR
     */
    public static void bayar(String namaPembeli){
        Pembeli pembeli = cariPembeli(namaPembeli);
        System.out.println(namaPembeli + " berhasil melakukan pembelian barang dan pembayaran!");
        System.out.println("########## Detail Pembayaran ##########");
        cariPembeli(namaPembeli).cetakPesanan(pembeli); // memanggil method cetak pesanan

    }

    /*
     * Method untuk perintah RESTOCK
     */
    public static void restock(String namaBarang, int jumlah){
        if (jumlah <= 0){ // tidak bisa restock dengan jumlah 0 atau angka negatif
            System.out.println("Maaf, stok tambahan yang dimasukkan tidak valid");
        }

        else{
            cariBarang(namaBarang).setStok(cariBarang(namaBarang).getStok() + jumlah); // menambahkan barang sesuai jumlah yang diminta
            System.out.println("Berhasil menambahkan stok barang Aqua. Sisa stok sekarang = " + cariBarang(namaBarang).getStok());
        }
    }

    /*
     * Method untuk perintah DISKON.
     */
    public static void diskon(String namaPembeli){
        // mencetak diskon yang didapatkan
        System.out.println(namaPembeli + " mendapatkan diskon sebanyak " + hitungDiskon(cariPembeli(namaPembeli)) +"%");

    }

    /*
     * Method untuk mencari persentase diskon yang didapat oleh pembeli.
     */
    public static int hitungDiskon(Pembeli pembeli){
        int diskon = 0;

        //menghitung diskon yang didapatkan berdasarkan jumlah barang yang dipesan
        for(int i = 0; i < pembeli.getListPesanan().length; i++){
            if (pembeli.getListPesanan()[i] != null){
                diskon += pembeli.getListPesanan()[i].getJumlahBarang();
            }
        }
        return diskon;
    }

    /*
     * Method untuk mencari Barang berdasarkan nama
     */
    public static Barang cariBarang(String nama){
        for(Barang barang: listBarang){
            if(barang.getNama().equals(nama))
                return barang;
        }
        return null;
    }

    /*
     * Method untuk mencari Pembeli berdasarkan nama
     */
    public static Pembeli cariPembeli(String nama){
        for(Pembeli pembeli: listPembeli){
            if(pembeli.getNama().equals(nama))
                return pembeli;
        }
        return null;
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
