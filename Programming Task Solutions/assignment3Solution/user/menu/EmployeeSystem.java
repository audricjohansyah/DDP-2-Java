package assignment3Solution.user.menu;

import assignment3Solution.nota.Nota;
import assignment3Solution.user.Employee;
import assignment3Solution.user.Member;

import static assignment3Solution.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> cuci();
            case 2 -> displayNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    private void displayNota() {
        for (Nota nota:
             notaList) {
            System.out.println(nota.getNotaStatus());
        }
    }

    public void cuci() {
        System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());
        for (Nota nota:
             notaList) {
            System.out.println(nota.kerjakan());
        }
    }

    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}
