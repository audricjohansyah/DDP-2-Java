public class Beverage {
    protected String name;
    protected String size;
    protected boolean isCold;
    protected int price;
  
    public Beverage(String name, String size, boolean isCold) {
      this.name = name;
      this.size = size;
      this.isCold = isCold;
    }
  
    // Method sengaja dikosongkan
    public void calculatePrice() {
    }
  
    public String toString() {
      String output = "";
  
      if (isCold) {
        output += "COLD ";
      } else {
        output += "HOT ";
      }
  
      output += this.size.toUpperCase() + " " + this.name;
      return output;
    }

    // method untuk mendapatkan nama pemesan
    public String getName(){
      return this.name;
    }

    // method untuk menghitung harga yang harus dibayar
    public int getPrice(){
      calculatePrice();
      return this.price;
    }
  }