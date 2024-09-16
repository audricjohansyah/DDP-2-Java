package assignment3;
import assignment3.user.Member;
import assignment3.user.menu.EmployeeSystem;
import assignment3.user.menu.MemberSystem;
import assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password){
        String id = makeId(nama, noHp);
        if(!memberSystem.isMemberExist(id)){
            Member newMember = new Member(nama, id, password);
            memberSystem.addMember(newMember);
            return newMember;
        }
        return null;
    }

    // method untuk membuat id member
    public static String makeId(String nama, String noHP){
         String[] arrOfNama = nama.split(" ");
         String firstName = arrOfNama[0].toUpperCase();
         
         int sumOfNoHP = 0;
         for (int i = 0; i < noHP.length(); i++) {
             char num = noHP.charAt(i);
             if (Character.isDigit(num)) {
                 int digit = Character.getNumericValue(num);
                 sumOfNoHP += digit;
             }
         }
         
         int sumOfName = 0;
         for (int i = 0; i < firstName.length(); i++){
             if (Character.isDigit(firstName.charAt(i))){
                 sumOfName += Character.getNumericValue(firstName.charAt(i));
             }
             else if(Character.isLetter(firstName.charAt(i))){
                 sumOfName += (firstName.charAt(i) - 'A' + 1);
             }
             else{
                 sumOfName += 7;
             }
         }
         
         int nomorID = sumOfNoHP + sumOfName + 7;
         String stringID = String.valueOf(nomorID);
         if (stringID.length() == 1){
             stringID = "0" + stringID;
         }
         else{
             stringID = stringID.substring(stringID.length()-2, stringID.length());
         }
         String id = firstName + "-" + noHP + "-" + stringID;
         return id;
    }
}