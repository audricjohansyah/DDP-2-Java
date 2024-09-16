package assignment3.nota.service;
// implementasi interface laundryservice
public class SetrikaService implements LaundryService{
    private int workCount = 0;

    // mengubah isdone menjadi true
    @Override
    public String doWork() {
        workCount += 1;
        isDone();
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        if (workCount > 0){
            return true;
        }
        return false;
    }

    // menghitung harga setrika service
    @Override
    public long getHarga(int berat) {
        long harga = berat*1000;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
