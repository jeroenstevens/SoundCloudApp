package soundcloudapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import soundcloudapp.R;
import soundcloudapp.model.Playlist;
import soundcloudapp.model.Track;
import soundcloudapp.adapter.MusicListAdapter;
import soundcloudapp.service.MusicPlaybackService;

public class MusicListActivity extends Activity {

    private static final String TAG = "MusicListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.music_list_view);

        ListView musicListView = (ListView) findViewById(R.id.music_list_view);

        for(int i = 0; i < 5; i++) {
            Playlist.addTrack(new Track());
        }

        musicListView.setAdapter(new MusicListAdapter(this));

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick : " + i);
                Intent intent = new Intent(MusicPlaybackService.SERVICE_PLAY);
                intent.putExtra("track", Playlist.getTrack(i));
                sendBroadcast(intent);
            }
        });

        startService(new Intent(this, MusicPlaybackService.class));
    }
}
