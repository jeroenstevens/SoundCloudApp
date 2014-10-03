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

        setContentView(R.layout.music_list_view);

        mMusicListView = (ListView) findViewById(R.id.music_list_view);

        if (Playlist.getSize() == 0) {
            fetchTracks();
        }

        mMusicListView.setAdapter(new MusicListAdapter(MusicListActivity.this));

        mMusicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick : " + i);
                Intent intent = new Intent(MusicPlaybackService.SERVICE_TOGGLE_PLAY);
                intent.putExtra("track", Playlist.getTrack(i));
                sendBroadcast(intent);
            }
        });

        startService(new Intent(MusicListActivity.this, MusicPlaybackService.class));
    }

    public void fetchTracks() {

        new AsyncTask<String, Object, Object>() {

            protected Object doInBackground(String... params) {

                try {
                    URL url = new URL(params[0]);
                    Log.i(TAG, url.toString());
                    // Create a connection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    connection.connect();

                    // Log Response code and message
                    Log.i(TAG, "status: "+ connection.getResponseCode());
                    Log.i(TAG, "status message: "+ connection.getResponseMessage());

                    InputStream inputStream = connection.getInputStream();

                    String jsonString = InpuStreamConverter.toString(inputStream);

                    connection.disconnect();

                    return Json.fromStringToJson(jsonString);

                } catch (ProtocolException  e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            protected void onPostExecute(Object json) {

                JSONObject jsonObject = (JSONObject) json;

                JSONArray tracks = null;
                try {
                    tracks = jsonObject.getJSONArray("tracks");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (tracks != null) {
                    for (int i = 0; i < tracks.length(); i++) {

                        try {
                            JSONObject track = tracks.getJSONObject(i);

                            Playlist.addTrack(new Track(
                                    track.getString("id"),
                                    track.getString("name"),
                                    track.getJSONArray("artists").getJSONObject(0).getString("name"),
                                    track.getString("preview_url")
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    mMusicListView.invalidateViews();
                }
            }
        }.execute(SPOTIFY_API_URL);

    }
}
