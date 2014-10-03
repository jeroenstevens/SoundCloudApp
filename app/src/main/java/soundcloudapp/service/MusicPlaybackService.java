package soundcloudapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

import soundcloudapp.R;
import soundcloudapp.model.Playlist;
import soundcloudapp.model.Track;

public class MusicPlaybackService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final String TAG = "MusicPlaybackService";

    private static final String SERVICE_PREFIX = "soundcloudapp.";

    public static final String SERVICE_TOGGLE_PLAY = SERVICE_PREFIX + "TOGGLE_PLAY";

    private MediaPlayer sMediaPlayer;
    private NotificationManager mNotificationManager;
    private Track mCurrentTrack;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();

        mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        sMediaPlayer = new MediaPlayer();
        sMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        sMediaPlayer.setOnPreparedListener(this);
        sMediaPlayer.setOnCompletionListener(this);

        setBroadcastReceiver();
    }

    private void prepareThenPlay(Track track) {
        try {
            sMediaPlayer.reset();
            sMediaPlayer.setDataSource(track.streamUrl);
            sMediaPlayer.prepare();
            sendTicker(track);
            mCurrentTrack = track;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPrepared");
        mediaPlayer.start();
    }

    private void togglePlay(Track track) {
        if (sMediaPlayer.isPlaying() && mCurrentTrack.id.equals(track.id)) {
            sMediaPlayer.pause();
        } else {
            prepareThenPlay(track);
        }
    }

    private void setBroadcastReceiver() {

        BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d(TAG, action);

                if (action.equals(SERVICE_TOGGLE_PLAY)) {
                    Track track = (Track) intent.getSerializableExtra("track");
                    togglePlay(track);
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SERVICE_TOGGLE_PLAY);
        registerReceiver(mIntentReceiver, intentFilter);
    }

    private void sendTicker(Track track) {
        Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker(track.artist + " - " + track.title)
                .setContentTitle(track.title)
                .setContentText(track.artist)
                .build();

        mNotificationManager.notify(0, notification);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        prepareThenPlay(Playlist.getNextTrack(mCurrentTrack));
    }
}
