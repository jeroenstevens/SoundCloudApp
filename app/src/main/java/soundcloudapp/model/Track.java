package soundcloudapp.model;

import java.io.Serializable;

public class Track implements Serializable {

    public String title;
    public String artist;
    public String streamUrl;

    public Track() {
        title = "Title";
        artist = "Artist";
        streamUrl = "https://d2pdfwfdhlv7he.cloudfront.net/article_parts/14431/audio.mp3";
    }

}
