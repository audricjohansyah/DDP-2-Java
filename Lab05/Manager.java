public class Manager extends Employee{
    // atribut class manager
    private double raise;
  
    // constructor class manager
    Manager (String nama, double raise){
      super(nama);
      this.raise = raise;
    }
    
    // method override tostring class manager
    @Override
    public String toString() {
      String output = "";
      output += "Nama: " + this.getNama() + '\n';
      output += "Pengalaman Kerja: " + this.getPengalamanKerja() + '\n';
      output += "Status: " + this.getStatus() + '\n';
      output += "NetWorth: Rp" + String.format("%.2f",this.getNetWorth()) + '\n';
      output += "Jabatan : " + this.getJabatan() + '\n';
      output += "Role: Manager" + '\n';
      return output;
    }
    
    // method override nextyear class manager
    @Override
    void nextYear(int n){
      // set up variable yg dibutuhkan
      Double currentNetWorth = 0.0;
      Double newGaji = 0.0;
      this.setGaji(2000000);

      // mengecek jika manager masih status aktif
      if (this.getStatus() == true){
        for (int i = 0; i < n; i++){ // memasang jabatan dan menghitung pengalaman kerja
          this.setPengalamanKerja(this.getPengalamanKerja() + 1);
          if (this.getPengalamanKerja() <= 5){
            this.setJabatan("Junior");
          }
          else if (this.getPengalamanKerja() > 5 && this.getPengalamanKerja() <= 10){
            this.setJabatan("Senior");
          }

          else if(this.getPengalamanKerja() > 10 && this.getPengalamanKerja() <= 15){
            this.setJabatan("Expert");
          }

          else if (this.getPengalamanKerja() > 15){
              this.setPengalamanKerja(16);
              this.pensiun();
              break;
          }
        }
      }

      // menghitung net worth manager berdasarkan tahun pengalaman kerja
      if (this.getPengalamanKerja() <= 15){
        for(int i = 0; i < this.getPengalamanKerja(); i++){
          newGaji = this.getGaji()*1.25;
          this.setGaji(newGaji);
          currentNetWorth += newGaji;
        }
      }
      else{
        for(int i = 0; i < 15; i++){
          newGaji = this.getGaji()*1.25;
          this.setGaji(newGaji);
          currentNetWorth += newGaji;
        }
      }

      // memasang networth manager
      this.setNetWorth(currentNetWorth);
    }
}