public class Coffee extends Beverage {
    protected boolean hasWhipCream = false;
  
    public Coffee(String nama, String size, boolean isCold) {
      super(nama, size, isCold);
    }
  
    @Override
    // method untuk menghitung harga sesuai komposisi
    public void calculatePrice() {
      this.price = 0;
      if (this.hasWhipCream){
        this.price += 5000;
      }
      if (this.size.equalsIgnoreCase("tall")){
        this.price += 20000;
      }
      else if (this.size.equalsIgnoreCase("grande")){
        this.price += 25000;
      }
      else if(this.size.equalsIgnoreCase("venti")){
        this.price += 30000;
      }
    }
  
    // method untuk inisiasi whip cream pada kopi
    public void addWhipCream() {
      this.hasWhipCream = true;
    }
  
    @Override
    public String toString() {
      String res = super.toString();
      
      if (hasWhipCream) {
        res += " with Whip Cream";
      }
      res += " Rp. " + super.getPrice() + ",-";
  
      return res;
    }
  }