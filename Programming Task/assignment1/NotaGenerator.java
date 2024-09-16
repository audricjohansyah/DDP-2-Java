// import library yang diperlukan
package assignment1;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // variable boolean untuk infinite loop
        boolean temp = true;

        // memulai infinite loop
        while (temp){
            printMenu(); // memanggil method print menu untuk display main menu
            System.out.print("Pilihan : ");
            String option = input.nextLine(); // meminta perintah dari user
            System.out.println("================================");

            if (option.equals("1")){
                // meminta data dari user
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHP = input.nextLine();

                // validasi nomor hp dari user jika bukan digit dan meminta kembali hingga benar
                while (!nomorHP.matches("\\d+")){
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHP = input.nextLine();
                }

                // memanggil method membuat id bagi user
                String id = generateId(nama, nomorHP);
                System.out.println("ID Anda : " + id);
            }

            else if (option.equals("2")){
                // meminta data dari user
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHP = input.nextLine();
                while (!nomorHP.matches("\\d+")){ // validasi nomor hp seperti pada perintah 1
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHP = input.nextLine();
                }
                String id = generateId(nama, nomorHP);
                System.out.println("Masukkan tanggal terima:");
                String tanggalLaundry = input.nextLine();
                System.out.println("Masukkan paket laundry:");
                String paketLaundry = input.nextLine().toLowerCase();

                // validasi paket laundry jika paket tidak tersedia dan meminta kembali hingga benar
                while (!(paketLaundry.equals("express") || paketLaundry.equals("fast") || paketLaundry.equals("reguler"))){
                    if (paketLaundry.equals("?")){
                        showPaket(); // memanggil method yang menunjukkan paket yang tersedia jika input user "?"
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();
                    }

                    else{
                        System.out.println("Paket " + paketLaundry + " tidak diketahui" + '\n' + "[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();


                        if (paketLaundry.equals("?")){
                        showPaket(); // memanggil method yang menunjukkan paket yang tersedia jika input user "?"
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();
                        }
                    }
                }

                System.out.println("Masukkan berat cucian Anda [Kg]:");
                String beratLaundry = input.nextLine();

                // validasi berat laundry jika input bukan digit atau input angka negatif atau 0
                while (!beratLaundry.matches("\\d+") || beratLaundry.equals("0")){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratLaundry = input.nextLine();
                }

                int berat = Integer.valueOf(beratLaundry);
                String nota = generateNota(id, paketLaundry, berat, tanggalLaundry);
                System.out.println(nota);
            }

            else if (option.equals("0")){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                temp = false; // memberhentikan infinite loop
            }

            else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    // method untuk membuat id
    public static String generateId(String nama, String nomorHP){
        // split nama user berdasarkan spasi
        String[] arrOfNama = nama.split(" ");
        String firstName = arrOfNama[0].toUpperCase(); // mengambil nama pertama user
        
        // menjumlahkan setiap angka di nomor hp
        int sumOfNoHP = 0;
        for (int i = 0; i < nomorHP.length(); i++) {
            char num = nomorHP.charAt(i);
            if (Character.isDigit(num)) {
                int digit = Character.getNumericValue(num);
                sumOfNoHP += digit;
            }
        }
        
        // menjumlahkan value huruf nama pertama user
        int sumOfName = 0;
        for (int i = 0; i < firstName.length(); i++){
            if (Character.isDigit(firstName.charAt(i))){
                sumOfName += Character.getNumericValue(firstName.charAt(i));
            }
            else if(Character.isLetter(firstName.charAt(i))){
                sumOfName += (firstName.charAt(i) - 'A' + 1);
            }
            else{
                sumOfName += 7;
            }
        }
        
        // membuat id user
        int nomorID = sumOfNoHP + sumOfName + 7;
        String stringID = String.valueOf(nomorID);
        if (stringID.length() == 1){
            stringID = "0" + stringID;
        }
        else{
            stringID = stringID.substring(stringID.length()-2, stringID.length());
        }
        String id = firstName + "-" + nomorHP + "-" + stringID;
        return id;
    }

    // method untuk membuat nota
    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // membuat format tanggal
        LocalDate laundryDate = LocalDate.parse(tanggalTerima, formatter);
        String tanggalSelesai = "";

        // mengecek berat cucian dari user jika kurang dari 2kg
        if (berat < 2){
            berat = 2;
        }
        
        // mengolah data berdasarkan paket yang dipilih user
        int packagePrice = 0;
        if (paket.equals("express")){
            packagePrice = 12000;
            LocalDate newDate = laundryDate.plusDays(1);
            tanggalSelesai = newDate.format(formatter);

        }
        else if (paket.equals("fast")){
            packagePrice = 10000;
            LocalDate newDate = laundryDate.plusDays(2);
            tanggalSelesai = newDate.format(formatter);
            
        }
        else if (paket.equals("reguler")){
            packagePrice = 7000;
            LocalDate newDate = laundryDate.plusDays(3);
            tanggalSelesai = newDate.format(formatter);
        }
        int price = berat * packagePrice;
        
        // mencetak output nota user
        String outputNota = "";
        outputNota = "ID    : " + id + '\n' + "Paket : " + paket + '\n' + "Harga :" 
        + '\n' + berat + " kg x " + packagePrice + " = " + price + '\n' + "Tanggal Terima  : " 
        + tanggalTerima + '\n' + "Tanggal Selesai : " + tanggalSelesai;

        return outputNota;
    }
}

// referensi : https://stackoverflow.com/questions/16183467/difference-between-d-and-d-in-java-regex
//             https://www.javatpoint.com/java-int-to-string
//             https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
//             https://www.geeksforgeeks.org/localdate-plusdays-method-in-java-with-examples/