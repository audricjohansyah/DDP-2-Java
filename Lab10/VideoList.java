// TODO: Implementasi Class berikut
//  Hal yang perlu diimplementasi:
//  -) Buat class ini dapat menerima object apapun yang berupa Video
//  -) Atribut videoList
//  -) Constructor
//  -) Method insertVide(), deleteVideo(), getVideoList(), getFirst()

import java.util.ArrayList;
import java.util.List;

public class VideoList<T extends Video>{
    private List<T> listVideo;

    // constructor
    public VideoList() {
        listVideo = new ArrayList<>();
    }

    // method masukin video
    public void insertVideo(T video, boolean isBack) {
        if(isBack){
            listVideo.add(video);
        }
        else{
            listVideo.add(0, video);
        }
    }

    // method delete video
    public T deleteVideo() {
        if(listVideo.isEmpty()){
            return null;
        }
        return listVideo.remove(0);
    }

    // method mendapatkan list video
    public List<T> getVideoList() { 
        return listVideo;
    }

    // method mendapatkan video pertama
    public T getFirst() {
        if (!listVideo.isEmpty()) {
            return listVideo.get(0);
        } 
        else {
            return null;
        }
    }

}
