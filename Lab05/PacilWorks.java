// import library yang dibutuhkan
import java.util.ArrayList;
import java.util.Scanner;

public class PacilWorks {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
  
    private static void printSeparator() {
        System.out.println("=".repeat(64));
    }

    private static void daftarEmployee() {
        if(employees.size() == 0){
            System.out.println("PacilWorks tidak memiliki karyawan :(");  
            printSeparator();
            return;
        }
        printSeparator();
        System.out.printf("PacilWorks memiliki %d total karyawan:\n", employees.size());
        for(Employee e : employees) {
            System.out.println("- " + e);
        }
        printSeparator();
    }
    
    private static void nextYear(int n) {
        for(Employee e : employees) {
            e.nextYear(n);
        }
    }

    private static void handleAddEmployee(){
        System.out.print("Masukkan role employee: ");
            String role = in.nextLine();
                
        if(role.equalsIgnoreCase("Engineer")) {
            // menambahkan engineer ke array employees
            System.out.print("Nama: ");
            String namaEngineer = in.nextLine();
            System.out.print("Banyak Side Jobs: ");
            String sideJobs = in.nextLine();
            int inputSideJobs = Integer.parseInt(sideJobs);
            Engineer engineer = new Engineer(namaEngineer, inputSideJobs);
            employees.add(engineer);

        } else if(role.equalsIgnoreCase("Secretary")) {
            // menambahkan secretary ke array employees
            System.out.print("Nama: ");
            String namaSecretary = in.nextLine();
            System.out.print("Banyak Tunjangan: ");
            String tunjangan = in.nextLine();
            double inputTunjangan = Double.parseDouble(tunjangan);
            Secretary secretary = new Secretary(namaSecretary, inputTunjangan);
            employees.add(secretary);


        } else if(role.equalsIgnoreCase("Manager")) {
            // menambahkan manager ke array employees
            System.out.print("Nama: ");
            String namaManager = in.nextLine();
            Manager manager = new Manager(namaManager, 1.25);
            employees.add(manager);
            
        } else {
            System.out.println("Masukkan tidak sesuai. Silahkan coba lagi!");
        }
    }
        
    public static void main(String[] args) {
        System.out.print("Selamat datang di PacilWorks!");
        System.out.print("\n");

        while(true) {
            System.out.printf("" +
            "Silakan pilih salah satu opsi berikut:\n" +
            "[1] Daftar Karyawan\n" +
            "[2] Tambah Karyawan\n" +
            "[3] Simulasi n-tahun berikutnya\n" +
            "[*] Keluar\n");
            printSeparator();

            System.out.print("Input: ");
            String pilihan = in.nextLine();
            
            if(pilihan.equals("1")) {
                daftarEmployee();
            } else if(pilihan.equals("2")) {
                handleAddEmployee();
            } else if(pilihan.equals("3")) {
                System.out.print("Masukkan banyak tahun yang ingin disimulasikan: ");
                int banyakTahun = Integer.parseInt(in.nextLine());
                nextYear(banyakTahun);
                System.out.printf("%d tahun telah berlalu...\n", banyakTahun);
                printSeparator();
            } else {
                System.out.println("Terima kasih telah menggunakan layanan PacilWorks ~ !");
                break;
            }
        }
        
        in.close();
    }
}