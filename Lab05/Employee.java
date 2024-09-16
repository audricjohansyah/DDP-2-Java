public class Employee {
    // TODO lengkapi visibility modifier atribut dan methods berikut
    private String nama;
    private int pengalamanKerja;
    private boolean status;
    private double netWorth;
    private String jabatan;
    private double gaji;
  
    Employee(String nama){
      this.nama = nama;
      this.pengalamanKerja = 0;
      this.status = true;
      this.netWorth = 0;
      this.jabatan = "Junior";
    }
    
    void nextYear(int tahun) {
       this.pengalamanKerja += tahun;
    }
  
    // method getter dan setter class employee
    public String getNama() {
          return nama;
      }
  
    public void setJabatan(String jabatan){
      this.jabatan = jabatan;
    }
  
    public void setNama(String nama) {
        this.nama = nama;
    }
  
    public int getPengalamanKerja(){
      return pengalamanKerja;
    }
  
    public double getNetWorth(){
      return netWorth;
    }
    
    public void setNetWorth(double n){
      netWorth = n;
    }
  
    public void setGaji(double gaji) {
      this.gaji = gaji;
    }
  
    public double getGaji() {
      return gaji;
    }

    public boolean getStatus(){
      return status;
    }

    public void setStatus(boolean status){
      this.status = status;
    }
    
    public String getJabatan(){
      return jabatan;
    }

    // method set status pensiun
    public void pensiun() {
      if (this.pengalamanKerja > 15){
        this.setJabatan("Pensiun");
        this.status = false;
      }
    }

    public void setPengalamanKerja(int pengalamanKerja){
      this.pengalamanKerja = pengalamanKerja;
    }
  }