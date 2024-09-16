package assignment3.nota.service;
// implementasi interface laundryservice
public class CuciService implements LaundryService{
    private int workCount = 0;

    // membuat isdone menjadi true
    @Override
    public String doWork() {
        workCount += 1;
        isDone();
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        if (workCount > 0){
            return true;
        }
        return false;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
