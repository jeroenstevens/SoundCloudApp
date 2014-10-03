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

        // Get the systems notification service
        // New MediaPlayer
        // set audio stream type
        // set OnPrepareListener
        // set OnCompletionListener
        // execute setBroadcastReceiver

    }

    private void prepareThenPlay(Track track) {

        // Reset mediaplayer
        // Set data source of mediaplayer
        // Prepare mediaplayer
        // Execute sendTicker
        // Make the send track the current track

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPrepared");
        mediaPlayer.start();
    }

    private void togglePlay(Track track) {

        // Pause if play and the same track
        // Else prepareThenPlay

    }

    private void setBroadcastReceiver() {

        // Create a BroadcastReceiver
        // onReceive
        //// Get the action from the passed intent
        //// if action SERVICE_TOGGLE_PLAY
        ///// Get the track from the intent
        ///// togglePlay
        // Create an IntentFilter with SERVICE_TOGGLE_PLAY
        // register the BroadcastReceiver with the IntentFilter

    }

    private void sendTicker(Track track) {

        // Create a notification
        //// small icon, ic_launcer
        //// ticker with track information
        //// setContentTitle
        //// setContentText
        //// build
        // Notify the notification with the NotificationManager

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

        // prepareThenPlay with the next track of the playlist

    }
}
