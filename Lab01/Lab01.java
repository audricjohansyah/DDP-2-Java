// import library scanner untuk input user
import java.util.*;

public class Lab01 {
  public static void main(String[] args){
    // welcome statement
    System.out.println("Selamat datang di Toko Fotokopi Dek Depe!");
    System.out.println("--------------------------------------------------------");

    // meminta input jumlah siswa dari user
    Scanner jumlahSiswa = new Scanner(System.in);
    System.out.print("Masukkan jumlah mahasiswa yang ingin melakukan fotokopi: ");
    int jlhSiswa = jumlahSiswa.nextInt();

    // set up variable untuk total harga
    Double totalHarga = 0.0;
    
    // loop untuk meminta data per mahasiswa
    for (int i = 0; i < jlhSiswa; i++){
      System.out.println("--------------------DATA MAHASISWA " + (i+1) + "--------------------");
      
      // meminta nama mahasiswa
      Scanner nama = new Scanner(System.in);
      System.out.print("Nama: ");
      String namaSiswa = nama.nextLine();
      
      // meminta ipk mahasiswa
      Scanner ipk = new Scanner(System.in);
      System.out.print("IPK: ");
      Double ipkSiswa = ipk.nextDouble();

      // meminta jumlah lembar yang akan di print
      Scanner jlhLembar = new Scanner(System.in);
      System.out.print("Jumlah lembar: ");
      int jumlahLembar = jlhLembar.nextInt();

      // set up variable untuk harga akhir dan besar diskon
      Double harga = 0.0;
      int diskon = 0;

      // evaluasi berapa besar diskon yang didapatkan berdasarkan ipk mahasiswa
      if (3.5 < ipkSiswa && ipkSiswa <= 4.0){
        diskon = 50;
      }
      else if (3.0 < ipkSiswa && ipkSiswa <= 3.5){
        diskon = 35;
      }
      else if (2.5 < ipkSiswa && ipkSiswa <= 3.0){
        diskon = 25;
      }
      else if(ipkSiswa <= 2.5){
        diskon = 10;
      }

      // memanggil method untuk mengevaluasi harga akhir dan menjumlahkan harga untuk ringkasan data
      harga = harga_akhir(diskon, jumlahLembar);
      totalHarga += harga;

      // mencetak output
      System.out.println(namaSiswa + " membayar seharga " + String.format("%.2f", harga) + " dengan diskon sebesar " + diskon + "%");

      // mencetak output ringkasan data di akhir loop
      if (i == (jlhSiswa-1)){
        System.out.println("---------------------RINGKASAN DATA---------------------");
        System.out.println("Hasil pendapatan yang diperoleh Toko Fotokopi dari " + jlhSiswa + " mahasiswa adalah " + String.format("%.2f", totalHarga));
      }
    }
  }

  
  // method untuk mengevaluasi harga yang perlu dibayar oleh mahasiswa
  public static Double harga_akhir(int discount, int jumlah){
    Double final_price = 0.0;
    final_price = 555.0 * jumlah * (100.0-discount) / 100.0;
    return final_price;
  }
}