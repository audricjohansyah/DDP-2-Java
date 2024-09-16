package assignment3.user.menu;

// import library
import assignment3.nota.Nota;
import assignment3.nota.NotaManager;
import assignment3.nota.service.AntarService;
import assignment3.nota.service.CuciService;
import assignment3.nota.service.LaundryService;
import assignment3.nota.service.SetrikaService;
import assignment3.user.Member;
import assignment1.NotaGenerator;
import static assignment3.nota.NotaManager.cal;
import static assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        if(choice == 1){
            displayMenuOne();
        }
        else if(choice == 2){
            displayMenuTwo();
        }
        else if (choice == 3){
            logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    // method menu satu
    protected void displayMenuOne(){
        NotaGenerator.showPaket();

        // minta paket laundry
        System.out.println("Masukkan paket laundry:");
        String paket = in.nextLine();
        while (!(paket.equalsIgnoreCase("express") || paket.equalsIgnoreCase("fast") 
        || paket.equalsIgnoreCase("reguler"))){
            System.out.println("Paket " + paket + " tidak diketahui.");
            System.out.println("Masukkan paket laundry:");
            paket = in.nextLine();
        }

        // minta berat cucian
        System.out.println("Masukan berat cucian anda [Kg]:");
        String berat = in.nextLine();
        while (!berat.matches("\\d+") || berat.equals("0")){
            System.out.println("Harap masukkan berat cucian Anda dengan benar.");
            berat = in.nextLine();
        }
        int beratLaundry = Integer.valueOf(berat);
        if (beratLaundry < 2){
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            beratLaundry = 2;
        }
        
        // membuat object nota member
        String tanggalLaundry = fmt.format(cal.getTime());
        Nota nota = new Nota(loginMember, beratLaundry, paket, tanggalLaundry);

        // menambahkan cuciservice ke nota
        LaundryService cuciService = new CuciService();
        nota.addService(cuciService);

        // menambahkan setrikaservice jika user mau
        System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0\n[Ketik x untuk tidak mau]: ");
        String setrika = in.nextLine();
        if(!setrika.equalsIgnoreCase("x")){
            LaundryService setrikaService = new SetrikaService();
            nota.addService(setrikaService);
        }

        // menambahkan antarservice jika user mau
        System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg\n[Ketik x untuk tidak mau]: ");
        String antar = in.nextLine();
        if(!antar.equalsIgnoreCase("x")){
            // System.out.println("masuk ANTAR");
            LaundryService antarService = new AntarService();
            nota.addService(antarService);
        }

        // menambahkan nota 
        NotaManager.addNota(nota);
        System.out.println("Nota berhasil dibuat!");
    }

    // method untuk menu dua
    protected void displayMenuTwo(){
        if(NotaManager.notaList.size() != 0){
            for (Nota nota : NotaManager.notaList) {
                if(nota.getMember().equals(loginMember)){
                    System.out.println(nota);
                }
            }
        }
        else{
            System.out.println("Member belum mencuci!");
        }
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        Member[] arrMember = new Member[memberList.length + 1];

        if(arrMember.length <= 1){
            for (int i = 0; i < arrMember.length; i++){
                arrMember[i] = member;
            }
        }

        else{
            for (int i = 0; i < arrMember.length; i++){
                try {
                    arrMember[i] = memberList[i];
                } 
                
                catch (Exception e) {
                    if (i == arrMember.length-1){
                        arrMember[i] = member;
                    }
                }
            }
        }

        memberList = arrMember;
    }

    // method untuk mendapatkan list member
    public Member[] getMembers(){
        return memberList;
    }
}