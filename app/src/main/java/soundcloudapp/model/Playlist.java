package soundcloudapp.model;

import java.util.Map;
import java.util.TreeMap;

public class Playlist {

    private static TreeMap<Integer, Track> sTracks = new TreeMap<Integer, Track>();
    private static Integer sCounter = 0;
    private static Integer sCurrentPosition;

    public static void addTrack(Track track) {
        sTracks.put(sCounter, track);
        sCounter++;
    }

    public static Track getTrack(Integer key) {
        sCurrentPosition = key;
        return sTracks.get(key);
    }

    public static Integer getSize() {
        return sCounter;
    }

    public static Track getNextTrack(Track track) {

        // Get the next Entry from the TreemMap based on a given position
        // If nextEntry null
        //// Set nextEntry as first entry
        // Set nextTrack to nextEntry's value
        // Set current position to nextEntry's value

        return null;
    }
}
