package assignment3.user.menu;

import assignment3.nota.Nota;
import assignment3.nota.service.LaundryService;
import assignment3.user.Employee;
import assignment3.user.Member;

import static assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
                new Employee("delta Epsilon Huha Huha", "ImplicitDiff"),
                new Employee("Regret", "FansBeratKanaArima")
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        if (choice == 1){
            displayMenuOne();
        }
        else if (choice == 2){
            displayMenuTwo();
        }
        else if (choice == 3){
            logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    // method untuk menu satu
    protected void displayMenuOne(){
        boolean allDone = false;

        // mengecek list nota kosong atau tidak
        if(notaList.size() != 0){
            System.out.println("Stand back! " + loginMember.getNama() + " beginning to nyuci!");

            // melaksanakan service pada semua nota
            for (Nota nota : notaList){
                for (LaundryService service : nota.getServices()){
                    if(service.isDone() == false){
                        System.out.println("Nota " + nota.getId() + " : " + service.doWork());
                        allDone = false;
                        break;
                    }
                    else{
                        allDone = true;
                    }
                }
                nota.setNotaStatus(); // mengubah status nota jika semua service sudah selesai
                if(allDone == true){ // output nota sudah selesai
                    System.out.println("Nota " + nota.getId() + " : " + nota.getNotaStatus());
                    continue;
                }
            }
        }

        else{
            System.out.println("Belum ada nota yang terdaftar di dalam sistem!");
        }
        
    }

    // method untuk menu dua
    protected void displayMenuTwo(){
        // mengecek list nota kosong atau tidak
        if (notaList.size() != 0){
            for(Nota nota: notaList){ // mencetak status semua nota di list nota
                System.out.println("Nota " + nota.getId() + " : " + nota.getNotaStatus());
            }
        }
        else{
            System.out.println("Belum ada nota yang terdaftar di dalam sistem!");
        }
    }
}