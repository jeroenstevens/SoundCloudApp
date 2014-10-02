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
import soundcloudapp.model.Track;

public class MusicPlaybackService extends Service implements MediaPlayer.OnPreparedListener {

    private static final String TAG = "MusicPlaybackService";

    private static final String SERVICE_PREFIX = "soundcloudapp.";

    public static final String SERVICE_PLAY = SERVICE_PREFIX + "PLAY";
    public static final String SERVICE_RESUME_PLAYING = SERVICE_PREFIX + "RESUME_PLAYING";
    public static final String SERVICE_PAUSE = SERVICE_PREFIX + "PAUSE";

    private static MediaPlayer sMediaPlayer;
    private NotificationManager mNotificationManager;

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

        setBroadcastReceiver();
    }

    private void prepareThenPlay(Track track) {
        try {
            sMediaPlayer.reset();
            sMediaPlayer.setDataSource(track.streamUrl);
            sMediaPlayer.prepare();
            sendTicker(track);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPrepared");
        togglePlay();
    }

    private void togglePlay() {
        if (sMediaPlayer.isPlaying()) {
            sMediaPlayer.pause();
        } else {
            sMediaPlayer.start();
        }
    }

    private void setBroadcastReceiver() {

        BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d(TAG, action);

                if (action.equals(SERVICE_PLAY)) {
                    prepareThenPlay((Track) intent.getSerializableExtra("track"));
                } else if (action.equals(SERVICE_PAUSE)) {
                    togglePlay();
                } else if (action.equals(SERVICE_RESUME_PLAYING)) {
                    togglePlay();
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SERVICE_PLAY);
        intentFilter.addAction(SERVICE_PAUSE);
        intentFilter.addAction(SERVICE_RESUME_PLAYING);
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
}
