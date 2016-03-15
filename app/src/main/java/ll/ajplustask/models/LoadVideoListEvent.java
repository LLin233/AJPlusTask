package ll.ajplustask.models;

import java.util.List;

/**
 * Created by Le on 2016/3/14.
 */
public class LoadVideoListEvent {
    private List<Item> mVideos;

    public LoadVideoListEvent(List<Item> videos) {
        this.mVideos = videos;
    }

    public List<Item> getVideoLists() {
        return mVideos;
    }
}
