package assignment2;

public class Member {

    // properti class member
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    // constructor class member
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
    }

    // method getter & setter class member
    public String getName(){
        return this.nama;
    }

    public String getNoHp(){
        return this.noHp;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setBonusCounter(int bonusCounter){
        this.bonusCounter = bonusCounter;
    }

    public int getBonusCounter(){
        return this.bonusCounter;
    }

}
