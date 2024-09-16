public class Tea extends Beverage {
    protected boolean hasMilk = false;

    public Tea(String nama, String size, boolean isCold) {
      super(nama, size, isCold);
    }

    @Override
    // method untuk menghitung harga sesuai komposisi
    public void calculatePrice() {
      this.price = 0;
      if (this.hasMilk){
        this.price += 7000;
      }
      if (this.size.equalsIgnoreCase("tall")){
        this.price += 15000;
      }
      else if (this.size.equalsIgnoreCase("grande")){
        this.price += 20000;
      }
      else if(this.size.equalsIgnoreCase("venti")){
        this.price += 25000;
      }    
    }

    // method untuk inisiasi susu pada teh
    public void addMilk() {
        this.hasMilk = true;
    }

    @Override
    public String toString(){
      String res = super.toString();

      if (hasMilk) {
        res += " with Milk";
      }
  
      res += " Rp. " + this.getPrice() + ",-";
  
      return res;
    }
}