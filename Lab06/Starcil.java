// import library yg dibutuhkan
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Starcil {
  private static InputReader in;
  private static PrintWriter out;
  private static Beverage[] daftarMinuman = new Beverage[0];

  // method unutk memasukkan minuman ke array
  private static void masukkanKeDaftarMinuman(Beverage beverage) {
    Beverage[] newDaftarMinuman = new Beverage[daftarMinuman.length + 1];

    for (int i = 0; i < daftarMinuman.length; i++) {
      newDaftarMinuman[i] = daftarMinuman[i];
    }
    daftarMinuman = newDaftarMinuman;

    newDaftarMinuman[daftarMinuman.length - 1] = beverage;
  }

  // method yang mereturn objek Beverage
  private static Beverage getMinuman(String namaMinuman) {
    for (Beverage drink : daftarMinuman) {
      if (drink.getName().equalsIgnoreCase(namaMinuman)) {
        return drink;
      }
    }
    return null;
  }

  public void mainProgram() {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N;
    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      String event = in.next();

      if (event.equals("ADD")) {
        String jenisMinuman = in.next();
        String nama = in.next();
        String size = in.next();
        boolean isCold = in.next().toUpperCase().equals("YES");

        // memasukkan minuman ke daftar minuman
        if (jenisMinuman.equals("COFFEE")) {
          // inisiasi object coffee
          Coffee kopi = new Coffee(nama, size, isCold);
          masukkanKeDaftarMinuman(kopi);

        } else {
          // inisiasi object tea
          Tea teh = new Tea(nama, size, isCold);
          masukkanKeDaftarMinuman(teh);
        }
      }

      else if (event.equals("TOPPING")) {
        String namaMinuman = in.next();
        Beverage minuman = getMinuman(namaMinuman);        

        // validasi input berupa class TEA atau COFFEE
        if (minuman instanceof Coffee){
          ((Coffee)minuman).addWhipCream();
          
        }
        else{
          ((Tea)minuman).addMilk();
        }
      }

      else if (event.equals("ORDERAN")) {
        String jenisMinuman = in.next();
        if (jenisMinuman.equals("COFFEE")) {
          // print seluruh coffee yang ada
          for (Beverage coffee : daftarMinuman){
            if (coffee instanceof Coffee){
              out.println(coffee);
            }
          }
        }
        else{
          // print seluruh tea yang ada
          for (Beverage tea : daftarMinuman){
            if (tea instanceof Tea){
              out.println(tea);
            }
          }
        }
      }

      else {
        out.println("PERINTAH TIDAK DITEMUKAN");
      }

      out.flush();
    }
  }

  public static void main(String[] args) {
    Starcil cafe = new Starcil();
    cafe.mainProgram();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the
  // usual Scanner(System.in) and System.out
  // please use these classes to avoid your fast algorithm gets Time Limit
  // Exceeded caused by slow input-output (IO)
  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}