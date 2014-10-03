package soundcloudapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import soundcloudapp.R;
import soundcloudapp.model.Playlist;
import soundcloudapp.model.Track;
import soundcloudapp.adapter.MusicListAdapter;
import soundcloudapp.service.MusicPlaybackService;
import soundcloudapp.utils.InpuStreamConverter;
import soundcloudapp.utils.Json;

public class MusicListActivity extends Activity {

    private static final String TAG = "MusicListActivity";
    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/artists/2ye2Wgw4gimLv2eAKyk1NB/top-tracks?country=NL";
    private ListView mMusicListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view
        // Get the ListView from the xml layout
        // Fetch the tracks of the api when playlist size is 0
        // Set an new instance of MusicListAdapter on the ListView
        // Set a OnItemClickListener on the ListView
        //// Make in the onItemClick an Intent with an action
        //// Send the track from the playlist with the intent to the service
        // Start the MusicPlaybackService

    }

    public void fetchTracks() {

        // Make a new AsyncTask<String, Object, Object>
        // Send the api url as parameter with the task
        // In doInBackground
        //// Make a new URL with the first parameter
        //// Make a http connection
        //// Get the InputStream from the connection
        //// Convert the InputStream to a String
        //// Disconnection the connection
        //// Convert and return the String as JSON
        // In onPostExecute()
        //// Typecast the return value to a json object
        //// Get the JSONArray of tracks
        //// Make a for loop
        ////// Get the track jsonobject
        ////// Create a new track with the information of the track jsonobject
        //// Update the rows in the ListView

    }
}
