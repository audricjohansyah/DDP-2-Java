public class Secretary extends Employee{
    // atribut class secretary
    private double tunjangan;
    private double jobEarnings;
    private double tunjanganEarnings;

    // constructor class secretary
    Secretary (String nama, double tunjangan){
      super(nama);
      this.tunjangan = tunjangan;
      this.jobEarnings = 0.0;
      this.tunjanganEarnings = 0.0;
    }
    
    // override method tostring class secretary
    @Override
    public String toString() {
      String output = "";
      output += "Nama: " + this.getNama() + '\n';
      output += "Pengalaman Kerja: " + this.getPengalamanKerja() + '\n';
      output += "Status: " + this.getStatus() + '\n';
      output += "NetWorth: Rp" + String.format("%.2f",this.getNetWorth()) + '\n';
      output += "Jabatan : " + this.getJabatan() + '\n';
      output += "Role: Secretary" + '\n';
      output += "Banyak Tunjangan: " + String.format("%.2f", this.tunjangan) + '\n';
      return output;
    }
    
    // override method nextyear class secretary
    @Override
    void nextYear(int n){

      // mengecek jika secretary masih status aktif
      if (this.getStatus() == true){
        for (int i = 0; i < n; i++){ // memasang jabatan, menghitung uang yang diperoleh dari pekerjaan dan sidejobs, menghitung pengalaman kerja
          this.setPengalamanKerja(this.getPengalamanKerja() + 1);
          if (this.getPengalamanKerja() <= 5){
            this.setJabatan("Junior");
            this.jobEarnings += 3000000;
            this.tunjanganEarnings += this.tunjangan;
          }
          else if (this.getPengalamanKerja() > 5 && this.getPengalamanKerja() <= 10){
            this.setJabatan("Senior");
            this.jobEarnings += 6000000;
            this.tunjanganEarnings += this.tunjangan;
          }

          else if(this.getPengalamanKerja() > 10 && this.getPengalamanKerja() <= 15){
            this.setJabatan("Expert");
            this.jobEarnings += 9000000;
            this.tunjanganEarnings += this.tunjangan;
          }

          else if (this.getPengalamanKerja() > 15){
              this.setPengalamanKerja(16);
              this.tunjanganEarnings = this.tunjangan*15;
              this.pensiun();
              break;
          }
        }
      }
      this.setNetWorth(this.jobEarnings + this.tunjanganEarnings); // memasang net worth secretary
    }
}