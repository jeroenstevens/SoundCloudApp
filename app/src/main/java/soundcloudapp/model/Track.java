package soundcloudapp.model;

import java.io.Serializable;

public class Track implements Serializable {

    public String id;
    public String title;
    public String artist;
    public String streamUrl;

    public Track(String id, String title, String artist, String streamUrl) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.streamUrl = streamUrl;
    }
}
