package soundcloudapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import soundcloudapp.R;
import soundcloudapp.model.Playlist;
import soundcloudapp.model.Track;

public class MusicListAdapter extends BaseAdapter {

    private static final String TAG = "MusicListAdapter";

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public MusicListAdapter(Context context) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return Playlist.getSize();
    }

    @Override
    public Track getItem(int position) {
        return Playlist.getTrack(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView : " + position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.music_list_view_row, null);
        }

        final Track track = getItem(position);

        TextView trackTitleTextView = (TextView) convertView.findViewById(R.id.track_title);
        TextView trackArtistTextView = (TextView) convertView.findViewById(R.id.track_artist);

        trackTitleTextView.setText(track.title);
        trackArtistTextView.setText(track.artist);

        return convertView;
    }
}
