package assignment3.nota;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    public static ArrayList<Nota> notaList = new ArrayList<Nota>();

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        cal.add(Calendar.DATE, 1);
        for(Nota nota: notaList){
            nota.toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        notaList.add(nota);
    }
}
