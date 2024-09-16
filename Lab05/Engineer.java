public class Engineer extends Employee {
  // atribut class engineer
    private int banyakSideJobs; 
    private double earningSideJobs;
    private double jobEarnings;

    // constuctor class engineer
    Engineer (String nama, int banyakSideJobs){
      super(nama);
      this.banyakSideJobs = banyakSideJobs;
      this.earningSideJobs = 0.0;
      this.jobEarnings = 0.0;
    }
  
    // override method tostring class engineer
    @Override
    public String toString() {
      String output = "";
      output += "Nama: " + this.getNama() + '\n';
      output += "Pengalaman Kerja: " + this.getPengalamanKerja() + '\n';
      output += "Status: " + this.getStatus() + '\n';
      output += "NetWorth: Rp" + String.format("%.2f",this.getNetWorth()) + '\n';
      output += "Jabatan : " + this.getJabatan() + '\n';
      output += "Role: Engineer" + '\n';
      output += "Banyak SideJobs: " + this.banyakSideJobs + '\n';
      return output;
    }
    
    // override method nextyear class engineer
    @Override
    void nextYear(int n){
      // kalkulasi uang yang didapatkan dari jumlah sidejobs
      Double uangsideJobs =  Double.valueOf(this.banyakSideJobs * 500000);

      // mengecek jika engineer masih status aktif
      if (this.getStatus() == true){
        for (int i = 0; i < n; i++){ // memasang jabatan, menghitung uang yang diperoleh dari pekerjaan dan sidejobs, menghitung pengalaman kerja
          this.setPengalamanKerja(this.getPengalamanKerja() + 1);
          if (this.getPengalamanKerja() <= 5){
            this.setJabatan("Junior");
            this.jobEarnings += 4000000;
            this.earningSideJobs += uangsideJobs;
          }
          else if (this.getPengalamanKerja() > 5 && this.getPengalamanKerja() <= 10){
            this.setJabatan("Senior");
            this.jobEarnings += 8000000;
            this.earningSideJobs += uangsideJobs;
          }

          else if(this.getPengalamanKerja() > 10 && this.getPengalamanKerja() <= 15){
            this.setJabatan("Expert");
            this.jobEarnings += 12000000;
            this.earningSideJobs += uangsideJobs;
          }

          else if (this.getPengalamanKerja() > 15){
              this.setPengalamanKerja(16);
              this.earningSideJobs = uangsideJobs * 15;
              this.pensiun();
              break;
          }
        }
      }
      this.setNetWorth(this.jobEarnings + this.earningSideJobs); // memasang networth engineer
    }
  }