package assignment3.nota.service;
// implementasi interface laundryservice
public class AntarService implements LaundryService{
    private int workCount = 0;
    
    // mengubah service isdone menjadi true
    @Override
    public String doWork() {
        workCount += 1;
        isDone();
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        if (workCount > 0){
            return true;
        }
        return false;
    }

    // menghitung harga antar service
    @Override
    public long getHarga(int berat) {
        long harga = 0;
        if (berat < 4){
            harga = 2000;
        }
        else if (berat >= 4){
            harga = berat*500;
        }
        
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
