// import library yang dibutuhkan
package assignment2;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Scanner;
import static assignment1.NotaGenerator.*;

public class MainMenu {
    // set up variable
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static Nota[] notaList;
    private static Member[] memberList;
    private static int jumlahMember = 0;
    private static int jumlahNota = 0;
    private static int idNota = -1;
    private static String tanggalLaundry = fmt.format(cal.getTime());
    private static int bonusCounter = 0;

    public static void main(String[] args) {
        // memulai infinte loop
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : "); // meminta perintah dari user
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    // method generate user (pilihan 1)
    private static void handleGenerateUser() {
        // meminta data nama dan nomor hp dair user
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda:");
        String nomorHP = input.nextLine();
        while (!nomorHP.matches("\\d+")){
            System.out.println("Field nomor hp hanya menerima digit.");
            nomorHP = input.nextLine();
        }

        // mengecek user id sudah ada di list member
        boolean found = false;
        if (jumlahMember != 0){
            String id = generateId(nama, nomorHP);
            for (int i = 0; i < memberList.length; i ++){
                if (memberList[i].getId().equals(id)){
                    found = true;
                }
            }
        }

        if (found){
            System.out.println("Member dengan nama " + nama + " dan nomor hp " + nomorHP + " sudah ada!");
        }

        // jika user id tidak ada di list member
        else{
            jumlahMember += 1;
            
            // membuat object member serta array member untuk dicopy
            Member calonMember = new Member (nama, nomorHP);
            Member[] arrMember = new Member[jumlahMember];
            String id = generateId(nama, nomorHP);
            calonMember.setId(id); // set id member
    
            // memasukkan member ke array yang akan dicopy
            if(jumlahMember <= 1){
                for (int i = 0; i < jumlahMember; i++){
                    arrMember[i] = calonMember;
                }
            }
    
            else{
                for (int i = 0; i < jumlahMember; i++){
                    try {
                        arrMember[i] = memberList[i];
                    } 
                    
                    catch (Exception e) {
                        if (i == jumlahMember-1){
                            arrMember[i] = calonMember;
                        }
                    }
                }
            }

            // mengcopy array
            memberList = arrMember;
            System.out.println("Berhasil membuat member dengan ID " + id); 
        }
        
    }

    // method untuk generate nota (pilihan 2)
    private static void handleGenerateNota() {

        // jika belum ada member tidak bisa membuat nota
        if (jumlahMember == 0){
            System.out.println("Belum ada member yang terdaftar di dalam sistem!");
        }

        else{
            boolean notFound = false;
            boolean found = false;

            // meminta data dari user
            System.out.println("Masukan ID member:");
            String id = input.nextLine();
            int indexMember = -1;

            // mengecek id user jika ada di list member
                for (int i = 0; i < memberList.length; i++){
                    if (!id.equals(memberList[i].getId())){
                        notFound = true;
                    }
                    
                    else{
                        notFound = false;
                        found = true;
                        indexMember = i;
                        break;
                    }
                }

            // id user tidak ditemukan
            if (notFound == true){
                System.out.println("Member dengan ID " + id + " tidak ditemukan!");
            }

            // id user ditemukan
            if(found == true){ 
                idNota += 1;
                jumlahNota += 1;

                // meminta data paket dan berat dari user
                System.out.println("Masukan paket laundry: ");
                String paketLaundry = input.nextLine().toLowerCase();
                while (!(paketLaundry.equals("express") || paketLaundry.equals("fast") || paketLaundry.equals("reguler"))){
                    if (paketLaundry.equals("?")){
                        showPaket();
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();
                    }

                    else{
                        System.out.println("Paket " + paketLaundry + " tidak diketahui" + '\n' + "[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();

                        if (paketLaundry.equals("?")){
                        showPaket();
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();
                        }
                    }
                }

                System.out.println("Masukkan berat cucian Anda [Kg]:");
                String beratLaundry = input.nextLine();
                while (!beratLaundry.matches("\\d+") || beratLaundry.equals("0")){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratLaundry = input.nextLine();
                }
                int berat = Integer.valueOf(beratLaundry);

                // membuat object nota serta array nota untuk dicopy
                Nota notaMember = new Nota(memberList[indexMember], paketLaundry, berat, tanggalLaundry);
                Nota[] arrNota = new Nota[jumlahNota];

                // memasukkan nota ke array yang akan dicopy
                if(jumlahMember <= 1){
                    for (int i = 0; i < jumlahNota; i++){
                        arrNota[i] = notaMember;
                    }
                }
        
                else{
                    for (int i = 0; i < jumlahNota; i++){
                        try {
                            arrNota[i] = notaList[i];
                        } 
                        
                        catch (Exception e) {
                            if (i == jumlahNota-1){
                                arrNota[i] = notaMember;
                            }
                        }
                    }
                }
                
                // mencopy array
                notaList = arrNota;

                // memasang tanggal selesai, id nota, dan sisa hari pengerjaan di object nota member
                String tanggalSelesai = " ";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate laundryDate = LocalDate.parse(tanggalLaundry, formatter);
                if (paketLaundry.equals("express")){
                    LocalDate newDate = laundryDate.plusDays(1);
                    tanggalSelesai = newDate.format(formatter);
        
                }
                else if (paketLaundry.equals("fast")){
                    LocalDate newDate = laundryDate.plusDays(2);
                    tanggalSelesai = newDate.format(formatter);
                    
                }
                else if (paketLaundry.equals("reguler")){
                    LocalDate newDate = laundryDate.plusDays(3);
                    tanggalSelesai = newDate.format(formatter);
                }
                LocalDate finishDate = LocalDate.parse(tanggalSelesai, formatter);
                int sisaHariPengerjaan = (int) ChronoUnit.DAYS.between(laundryDate, finishDate);
                notaMember.setIdNota(idNota);
                notaMember.setSisaHariPengerjaan(sisaHariPengerjaan);

                // memasang bonus counter pada member
                if(notaMember.getMember().getBonusCounter() <= 0){
                    bonusCounter = 0;
                    bonusCounter += 1;
                    notaMember.getMember().setBonusCounter(bonusCounter);
                }

                //menambahkan bonus counter pada member yang sama jika mereka mencuci kembali
                else{
                    notaMember.getMember().setBonusCounter(notaMember.getMember().getBonusCounter() + 1);
                }

                // mengecek bonus counter member
                int checkBonusCounter = notaMember.getMember().getBonusCounter();
                String nota = "";
                if (checkBonusCounter % 3 != 0){
                    nota = generateNota(id, paketLaundry, berat, notaMember.getTanggalMasuk());
                }
                else{ //reset bonus counter jika member sudah membuat nota sebanyak 3 kali
                    bonusCounter = 0;
                    notaMember.getMember().setBonusCounter(bonusCounter);
                    nota = generateNotaDiskon(id, paketLaundry, berat, notaMember.getTanggalMasuk());
                    
                }

                // mencetak output nota
                System.out.println("[ID Nota = "+ idNota + "]");
                System.out.println(nota);
                if(!tanggalLaundry.equals(tanggalSelesai)){
                    System.out.println("Status      	: Belum bisa diambil :(");
                }
            }
        }
    }

    // method untuk mengecek list nota (pilihan 3)
    private static void handleListNota() {

        // belum ada nota di dalam list
        if (notaList == null || notaList.length == 0){
            System.out.println("Terdaftar 0 nota dalam sistem.");
        }

        else{
            System.out.println("Terdaftar " + notaList.length + " nota dalam sistem.");
            for (int i = 0; i < notaList.length; i++){ // mengecek nota yang sudah siap diambil
                if (notaList[i].isReady()){
                    System.out.println("- [" + notaList[i].getIdNota() + "]" + " Status      	: Sudah dapat diambil !");
                }
                else{
                    System.out.println("- [" + notaList[i].getIdNota() + "]" + " Status      	: Belum bisa diambil :(");
                }
            }
        }
    }

    // method untuk mengecek list user/member (pilihan 4)
    private static void handleListUser() {

        // belum ada user yang mendaftar
        if (jumlahMember == 0){
            System.out.println("Terdaftar 0 member dalam sistem.");
        }

        else{ // mencetak id dan nama user yang terdaftar
            System.out.println("Terdaftar " + memberList.length + " member dalam sistem.");
            for (int i = 0; i < memberList.length; i++){;
                System.out.println("- " + memberList[i].getId() + " : " + memberList[i].getName());
            }
        }
    }

    // method untuk mengambil cucian (pilihan 5)
    private static void handleAmbilCucian() {

        // belum ada nota yang terdaftar sehingga tidak bisa mengambil cucian
        if (notaList == null || notaList.length == 0){
            System.out.println("Belum ada nota yang terdaftar di dalam sistem!");
        }

        else{

            // meminta id nota dan validasi id nota dari user
            System.out.println("Masukan ID nota yang akan diambil:");
            String idNotaDiambil = input.nextLine();
            while (!idNotaDiambil.matches("\\d+")){
                System.out.println("ID nota tidak valid!");
                idNotaDiambil = input.nextLine();
            }

            // mengecek apakah id nota ada belum selesai, tidak ada, atau sudah selesai
            int IdNotaRemove = Integer.valueOf(idNotaDiambil);
            int index = -1;
            boolean notDone = false;
            boolean done = false;
            boolean notFound = false;
            for (int i = 0; i < notaList.length; i++){
                if(IdNotaRemove == notaList[i].getIdNota()){
                    if(notaList[i].isReady()){
                        done = true;
                        notFound = false;
                        notDone = false;
                        index = i;
                        break;
                    }

                    else{
                        notDone = true;
                    }
                }

                else{
                    notFound = true;
                }
            }

            // nota belum selesai
            if (notDone == true){
                System.out.println("Nota dengan ID "+ IdNotaRemove + " gagal diambil!");
            }

            // nota tidak ada
            if (notFound == true){
                System.out.println("Nota dengan ID "+ IdNotaRemove + " tidak ditemukan!");
            }

            // nota sudah selesai
            if(done == true){
                System.out.println("Nota dengan ID "+ IdNotaRemove + " berhasil diambil!");
            }

            // menghapus nota dari list nota
            if (index != -1){
                jumlahNota -= 1;
                Nota[] arrNota = new Nota[jumlahNota];

                for (int i = 0; i < index; i++){
                    arrNota[i] = notaList[i];
                }
        
                for (int i = index + 1; i < notaList.length; i++) {
                    arrNota[i - 1] = notaList[i];
                }

                notaList = arrNota;
            }
        }
    }

    // method untuk menambahkan hari (pilihan 6)
    private static void handleNextDay() {

        // membuat format tanggal dan menambahkan tanggal sebanyak 1 hari
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate laundryDate = LocalDate.parse(tanggalLaundry, formatter);
        LocalDate newDate = laundryDate.plusDays(1);
        tanggalLaundry = newDate.format(formatter);

        System.out.println("Dek Depe tidur hari ini... zzz...");
        if (jumlahNota > 0){ // mengecek apakah ada nota yang sudah siap diambil
            for (int i = 0; i < jumlahNota; i++){
                notaList[i].setSisaHariPengerjaan(notaList[i].getSisaHariPengerjaan()-1);
                if(notaList[i].isReady()){
                    System.out.println("Laundry dengan nota ID " + notaList[i].getIdNota() + " sudah dapat diambil!");
                }
            }
        }
        System.out.println("Selamat pagi dunia!" + "\n" + "Dek Depe: It's CuciCuci Time.");
    }

    // method untuk print menu di main menu
    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", tanggalLaundry);
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    // method untuk membuat nota khusus mendapatkan diskon
    private static String generateNotaDiskon(String id, String paket, int berat, String tanggalTerima){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate laundryDate = LocalDate.parse(tanggalTerima, formatter);
        String tanggalSelesai = "";

        // mengecek berat cucian dari user jika kurang dari 2kg
        if (berat < 2){
            berat = 2;
        }
        
        // mengolah data harga dan tanggal selesai berdasarkan paket yang dipilih user
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
        
        String outputNota = "";
        outputNota = "ID    : " + id + '\n' + "Paket : " + paket + '\n' + "Harga :" 
        + '\n' + berat + " kg x " + packagePrice + " = " + price + " = " + price/2 + 
        " (Discount member 50%!!!)" + '\n' + "Tanggal Terima  : " + tanggalTerima + '\n' 
        + "Tanggal Selesai : " + tanggalSelesai;

        return outputNota;
    }
}