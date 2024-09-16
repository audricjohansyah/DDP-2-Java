// import library scanner untuk input user
import java.util.Scanner;

public class Lab02 {
    public static void main (String[] args){
        // variable boolean untuk infinite loop
        Boolean temp = true;

        // memulai infinite loop
        while (temp){

            // meminta perintah dari user
            System.out.println("Halo! Apa yang ingin kamu lakukan?");
            System.out.println("[1] Buat kupon");
            System.out.println("[2] Validasi kupon");
            System.out.println("[3] Keluar");
            System.out.print("Pilihan: ");
            Scanner inp = new Scanner(System.in);
            String pilihan = inp.nextLine();

            // jika user menginput perintah 1
            if (pilihan.equals("1")){
                // meminta kupon dari user
                System.out.print("Kupon: ");
                String kupon = inp.nextLine();

                // memanggil method untuk membuat kupon baru untuk user
                int lengthOfCoupon = kupon.length();
                String result = buatKupon(kupon, lengthOfCoupon);
                System.out.println("Kode kupon adalah: " + result);
                System.out.println("");
            }

            // jika user menginput perintah 2
            else if (pilihan.equals("2")){
                // meminta kupon dari user untuk dicek
                System.out.print("Kupon: ");
                String couponInput = inp.nextLine();

                String kupon = couponInput.substring(0, (couponInput.length()-1)-1); // memotong 2 karakter checksum kupon dari user
                int lengthOfCoupon = kupon.length();
                String checkCoupon = buatKupon(kupon, lengthOfCoupon); // membuat kupon yang seharusnya valid
                checkKupon(couponInput, checkCoupon); // memanggil method untuk mengecek kupon dari user
                System.out.println("");
            }

            // jika user menginput perintah 3
            else if (pilihan.equals("3")){
                //mematahkan infinite loop
                temp = false;
            }
        }
    }

    // method untuk membuat kupon
    public static String buatKupon(String coupon, int length){
        String kuponBaru = coupon;

        // base case (jika panjang kupon baru sudah sama dengan panjang kupon lama ditambah 2 character checksum)
        if(kuponBaru.length() == length + 2){
            return kuponBaru;
        }
        
        // recursive case
        else {
            // melakukan checksum
            int valueOfChar = 0;
            for (int i = 0; i < kuponBaru.length(); i++){
                valueOfChar += (kuponBaru.charAt(i) - 'A');
            }
            int nextLetter = valueOfChar % 26;
            kuponBaru = kuponBaru + (char)(nextLetter + 'A');
            return buatKupon(kuponBaru, length);
        }
    }

    // method untuk mengecek kupon valid
    public static void checkKupon(String coupon, String check){
        if (coupon.equals(check)){
            System.out.println("Kupon yang diberikan valid");
        }

        else{
            System.out.println("Kupon yang diberikan tidak valid");
        }
    }

}