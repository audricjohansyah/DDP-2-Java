package assignment3.nota;
import assignment3.nota.service.LaundryService;
import assignment3.user.Member;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
public class Nota {
    private Member member;
    private String paket;
    private ArrayList<LaundryService> services = new ArrayList<LaundryService>();
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;
    private long packagePrice;
    private int telat;
    private int hariTelatFinal;

    // constructor nota
    public Nota(Member member, int berat, String paket, String tanggalMasuk) {
        this.id = totalNota;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = getSisaHariPengerjaan();
        this.isDone = false;
        totalNota++;
    }

    // method untuk menambahkan service ke arraylist laundryservice
    public void addService(LaundryService service){
        services.add(service);
    }

    // method untuk skip satu hari
    public void toNextDay() {
        sisaHariPengerjaan--;

        // mengset hari telat jika nota belum selesai dan telat
        if(isDone == false){
            hariTelatFinal = getHariTelat();
        }
    }

    // menghitung harga
    public long calculateHarga(){
        if (paket.equalsIgnoreCase("express")){
            packagePrice = 12000;

        }
        else if (paket.equalsIgnoreCase("fast")){
            packagePrice = 10000;
        }
        else if (paket.equalsIgnoreCase("reguler")){
            packagePrice = 7000;
        }
        long price = berat * packagePrice;
        return price;
    }

    // mendapatkan output harga untuk nota
    public String outputHarga(){
        long harga = calculateHarga();
        return "\nHarga :\n" + getBerat() + " kg x " + packagePrice + " = " + harga;
    }

    // mendapatkan output laundry services untuk nota
    public String outputServices(){
        String output = "";
        long hargaAkhir = 0;

        // mendapatkan nama service
        for(LaundryService service : getServices()){
            output += "-" + service.getServiceName() + " @ " + "Rp." + service.getHarga(berat) + "\n";
            hargaAkhir += service.getHarga(berat);
        }

        // mengecek apakah nota sudah selesai atau belum
        if(isDone() == false){
            if(getHariTelat() > 0){ // nota belum selesai dan telat
                hargaAkhir += packagePrice*berat;
                hargaAkhir -= getHariTelat() * 2000;
                if(hargaAkhir <= 0){
                    hargaAkhir = 0;
                }
                output += "Harga Akhir: " + hargaAkhir + " Ada kompensasi keterlambatan " +
                getHariTelat() + " * 2000 hari" + "\n";
            }
            else{ // nota belum selesai dan tidak telat
                hargaAkhir += packagePrice*berat;
                output += "Harga Akhir: " + hargaAkhir + "\n";
            }
        }
        else{ 
            if(getHariTelat() > 0){
                if(hariTelatFinal == 0){ // nota sudah selesai dan tidak telat
                    hargaAkhir += packagePrice*berat;
                    output += "Harga Akhir: " + hargaAkhir + "\n";
                }
                else{ // nota sudah selesai dan telat
                    hargaAkhir += packagePrice*berat;
                    hargaAkhir -= hariTelatFinal * 2000;
                    if(hargaAkhir <= 0){
                        hargaAkhir = 0;
                    }
                    output += "Harga Akhir: " + hargaAkhir + " Ada kompensasi keterlambatan " +
                    hariTelatFinal + " * 2000 hari" + "\n";
                }
            }
            else{ // nota sudah selesai dan tidak telat
                hargaAkhir += packagePrice*berat;
                output += "Harga Akhir: " + hargaAkhir + "\n";
            }
        }

        return output;
    }

    // mengubah status nota
    public void setNotaStatus(){
        for(LaundryService service : services){
            if(service.isDone() == true){
                isDone = true;
            }
            else{
                isDone = false;
            }
        }
    }

    // mendapatkan status nota
    public String getNotaStatus(){
        if(isDone == false){
            return "Belum selesai.";
        }
        return "Sudah selesai.";
    }

    // override method tostring nota
    @Override
    public String toString(){
        return "[ID Nota = " + id + "]\n" + "ID    : " + member.getId() + "\nPaket : " 
        + paket.toLowerCase() + outputHarga() + "\ntanggal terima  : " + tanggalMasuk 
        + "\ntanggal selesai : " + getTanggalSelesai()
        + "\n--- SERVICE LIST ---\n" + outputServices();
    }
    

    // Dibawah ini adalah getter
    public Member getMember(){
        return member;
    }

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public boolean isDone() {
        return isDone;
    }

    public ArrayList<LaundryService> getServices(){
        return services;
    }

    public int getId(){
        return id;
    }

    public int getHariTelat(){
        if(sisaHariPengerjaan < 0){
            telat = -sisaHariPengerjaan;
        }
        return telat;
    }

    public int getSisaHariPengerjaan(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate laundryDate = LocalDate.parse(tanggalMasuk, formatter);
        String tanggalSelesai = getTanggalSelesai();
        LocalDate finishDate = LocalDate.parse(tanggalSelesai, formatter);
        int sisaHariPengerjaan = (int) ChronoUnit.DAYS.between(laundryDate, finishDate);
        return sisaHariPengerjaan;
    }

    public String getTanggalSelesai(){
        String tanggalSelesai = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate laundryDate = LocalDate.parse(tanggalMasuk, formatter);
        if (paket.equalsIgnoreCase("express")){
            LocalDate newDate = laundryDate.plusDays(1);
            tanggalSelesai = newDate.format(formatter);

        }
        else if (paket.equalsIgnoreCase("fast")){
            LocalDate newDate = laundryDate.plusDays(2);
            tanggalSelesai = newDate.format(formatter);
            
        }
        else if (paket.equalsIgnoreCase("reguler")){
            LocalDate newDate = laundryDate.plusDays(3);
            tanggalSelesai = newDate.format(formatter);
        }
        return tanggalSelesai;
    }
}