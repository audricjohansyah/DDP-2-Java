package assignment2;

public class Nota {

    // properti dari class nota
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady = false;
    
    // constructor nota
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
    }
    
    // method getter & setter class ntoa
    public Member getMember(){
        return this.member;
    }

    public String getMemberName(){
        return this.member.getName();
    }

    public String getPaket(){
        return this.paket;
    }

    public int getBerat(){
        return this.berat;
    }

    public String getTanggalMasuk(){
        return this.tanggalMasuk;
    }

    public void setIdNota(int idNota){
        this.idNota = idNota;
    }

    public int getIdNota(){
        return this.idNota;
    }

    public void setSisaHariPengerjaan(int sisaHariPengerjaan){
        this.sisaHariPengerjaan = sisaHariPengerjaan;
    }

    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }

    public boolean isReady(){
        if (this.getSisaHariPengerjaan() <= 0){
            this.isReady = true;
        }
        return this.isReady;
    }
}
