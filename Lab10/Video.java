public class Video {
    private String title;
    private int duration;
    public static int videoAmount;

    public Video(String title, int duration){
        this.title = title;
        this.duration = duration;
        videoAmount++;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.getTitle() + " - " + this.getDuration() + " menit";
    }
}
