import java.util.*;

public class Salary {
  public static void main(String[] args){
    Scanner jlh_karyawan = new Scanner(System.in);
    System.out.print("Berapa jumlah karyawan? ");
    int jumlah = jlh_karyawan.nextInt();

    for (int i = 0; i < jumlah; i++){
        Scanner name = new Scanner(System.in);
        System.out.print("Siapa nama karyawan? ");
        String nama = name.nextLine();
        Scanner gaji = new Scanner(System.in);
        System.out.print("Berapa gaji karyawan? ");
        int gaji_karyawan = gaji.nextInt();
        int thr = thr(gaji_karyawan);
        System.out.println("Gaji karyawan dengan nama " + nama + " adalah " + gaji_karyawan);
        System.out.println("THR karyawan dengan nama " + nama + " adalah " + thr);
        
    }
  }

  public static int thr(int gaji){
    int final_gaji = 0;
    if (gaji > 25000000){
        final_gaji = gaji*10/100;
    }
    else if(15000000 <= gaji && gaji <=25000000){
        final_gaji = gaji*15/100;
    }
    else if(5000000<= gaji && gaji <= 15000000){
        final_gaji = gaji*25/100;
    }
    else if(gaji<5000000){
        final_gaji = gaji*35/100;
    }
    return final_gaji;

}  
}