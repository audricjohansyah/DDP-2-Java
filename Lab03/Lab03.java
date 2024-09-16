// import library yang dibutuhkan
import java.util.Scanner;

class Lab03 {
    // set up variable awal dan scanner
    private static final Scanner input = new Scanner(System.in);
    private static int banyakMatkul;
    private static int jumlahSiswa = 0;
    private static String[] kumpulanNamaMahasiswa;
    private static String[] kumpulanNamaMatkul;
    private static int[][] score;

    public static void main(String[] args) {
        init(); // memanggil method inisiasi awal
        while (true) {
            printMenu(); // method print menu
            System.out.print("Masukkan pilihan: ");
            int pilihan = input.nextInt(); // meminta pilihan perintah dari user
            input.nextLine();
            switch (pilihan) {
                case 1:
                    menambahMahasiswa();
                    break;
                case 2:
                    menghapusMahasiswa();
                    break;
                case 3:
                    mencetakTabel();
                    break;
                case 4:
                    mencetakNilai();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan BeJayNG!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }

    // method untuk memasukkan mahasiswa dan nilai ke array
    public static void insertRow (int[] insertedRow, String namaMahasiswa) {

        //membuat array yang akan dicopy
        String[] arrMahasiswa = new String [jumlahSiswa];
        int[][] arrScore = new int[jumlahSiswa][kumpulanNamaMatkul.length];

        if(jumlahSiswa <= 1){
            // memasukkan nilai dan mahasiswa ke array yg akan dicopy
            for (int i = 0; i < jumlahSiswa; i++){
                arrMahasiswa[i] = namaMahasiswa;
                arrScore[i]= insertedRow;
            }
        }

        else{
            for (int i = 0; i < jumlahSiswa; i++){
                // memasukan nilai dan mahasiswa yang sudah dimasukkan ke array sebelumnya ke array yg akan dicopy
                try {
                    arrMahasiswa[i] = kumpulanNamaMahasiswa[i];
                    arrScore[i] = score[i];
                } 
                
                // memasukkan nilai dan mahasiswa yang belum dimasukkan ke array yg akan dicopy
                catch (Exception e) {
                    if (i == jumlahSiswa-1){
                        arrMahasiswa[i] = namaMahasiswa;
                        arrScore[i] = insertedRow;
                    }
                }
            }
        }

        // mengcopy array
        kumpulanNamaMahasiswa = arrMahasiswa;
        score = arrScore;
    }

    // method inisiasi
    static void init() {
        System.out.println("Selamat datang di BeJayNG!");
        System.out.println("==============Initial Input==============");
        System.out.print("Masukkan jumlah mata kuliah: ");
        banyakMatkul = input.nextInt(); // meminta banyak matkul dari user
        input.nextLine();
        kumpulanNamaMatkul = new String [banyakMatkul]; // set size array matkul berdasarkan banyak matkul

        // memasukkan matkul ke array
        for (int i = 0; i < banyakMatkul; i++){
            System.out.print("Masukkan nama mata kuliah: ");
            kumpulanNamaMatkul[i] = input.nextLine();
        }
    }

    // method print menu
    static void printMenu() {
        System.out.println("==============Menu==============");
        System.out.println("[1] Menambah Mahasiswa");
        System.out.println("[2] Menghapus Mahasiswa");
        System.out.println("[3] Mencetak Tabel");
        System.out.println("[4] Mencetak Nilai");
        System.out.println("[0] Keluar");
    }

    // method menambah mahasiswa
    static void menambahMahasiswa() {
        System.out.println("==============Menambah Mahasiswa==============");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMahasiswa = input.nextLine(); // meminta nama mahasiswa

        // membuat array untuk memasukkan nilai mahasiswa
        int[] arrayNilai = new int[kumpulanNamaMatkul.length];
        for (int i = 0; i < kumpulanNamaMatkul.length; i++){
            System.out.print("Masukkan nilai " + kumpulanNamaMatkul[i] + ": ");
            arrayNilai[i] = input.nextInt();
        }

        System.out.println("Nilai mahasiswa bernama " + namaMahasiswa + " berhasil diinput ke BeJayNG");
        jumlahSiswa ++; // menambahkan jumlah mahasiswa sebanyak satu
        insertRow(arrayNilai, namaMahasiswa); // memanggil method untuk memasukkan nilai dan mahasiswa ke array
    }

    // method untuk menghapus siswa
    static void menghapusMahasiswa() {
        System.out.println("==============Menghapus Mahasiswa==============");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMahasiswa = input.nextLine(); // meminta nama mahasiswa

        // mendapatkan index nama mahasiswa di array nama mahasiswa
        int index = -1;
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i ++){
            if (kumpulanNamaMahasiswa[i].equals(namaMahasiswa)){
                index = i;
                break;
            }
        }

        System.out.println("Mahasiswa bernama " + namaMahasiswa + " telah dihapus dari BeJayNG");
        jumlahSiswa -= 1; // mengurangi jumlah mahasiswa
        removeElement(index); // memanggil method untuk menghapus mahasiswa dan nilai dari array
    }

    // method untuk menghapus mahasiswa dan nilai dari array
    public static void removeElement(int index) {
      
        // membuat array yang akan dicopy
        String[] newKumpulanNamaMahasiswa = new String[jumlahSiswa];
        int[][] newScore = new int[jumlahSiswa][kumpulanNamaMatkul.length];

        // mengcopy data mahasiswa dan array sebelum index mahasiswa dan nilai yang akan dihapus
        for (int i = 0; i < index; i++){
            newKumpulanNamaMahasiswa[i] = kumpulanNamaMahasiswa[i];
            newScore[i] = score[i];
        }

        // mengcopy data mahasiswa dan array sesudah index mahasiswa dan nilai yang akan dihapus
        for (int i = index + 1; i < kumpulanNamaMahasiswa.length; i++) {
            newKumpulanNamaMahasiswa[i - 1] = kumpulanNamaMahasiswa[i];
            newScore[i - 1] = score[i];
        }
        
        // mengcopy array
        kumpulanNamaMahasiswa = newKumpulanNamaMahasiswa;
        score = newScore;
    }

    static void mencetakTabel() {
        System.out.println("==============Mencetak Tabel==============");

        // membuat header table
        String tableHeader = "Nama" + '\t';
        for (int i = 0; i < kumpulanNamaMatkul.length; i ++){
            tableHeader += '\t' + kumpulanNamaMatkul[i];
        }
        System.out.println(tableHeader);

        // membuat isi table
        String tableContent = "";
        for (int i = 0; i < jumlahSiswa; i++){
            tableContent = kumpulanNamaMahasiswa[i] + '\t';
            for(int j = 0; j < score[i].length; j++){
                tableContent += '\t' + Integer.toString(score[i][j]);
                
                // mencetak output jika data mahasiswa sudah selesai diambil
                if (j == score[i].length - 1){
                    System.out.println(tableContent);
                    tableContent = "";
                }
            }
        }
    }

    // method untuk mencetak nilai
    static void mencetakNilai() {
        System.out.println("==============Mencetak Nilai==============");
        System.out.print("Masukkan nama mahasiswa: "); // meminta nama mahasiswa
        String namaMahasiswa = input.nextLine();
        System.out.print("Masukkan nama mata kuliah: "); // meminta nama matkul
        String namaMatkul = input.nextLine();
        int nilai = 0; // variable untuk nilai yang akan dicetak

        // mencari index nama mahasiswa di array nama mahasiswa
        int indexMahasiswa = -1;
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i ++){
            if (kumpulanNamaMahasiswa[i].equals(namaMahasiswa)){
                indexMahasiswa = i;
                break;
            }
        }

        // mencari index nama matkul di array nama matkul
        int indexMatkul = -1;
        for (int i = 0; i < kumpulanNamaMatkul.length; i++){
            if (namaMatkul.equals(kumpulanNamaMatkul[i])){
                indexMatkul = i;
                break;
            }
        }

        // mencari nilai mahasiswa di array nilai
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i++){
            if(i == indexMahasiswa){
                for (int j = 0; j < score[i].length; j++){
                    if(j == indexMatkul){
                        nilai = score[i][j];
                        break;
                    }
                }
            }
        }
        System.out.println("Nilai " + namaMahasiswa + " di mata kuliah " + namaMatkul + " adalah " + nilai);
    }
}