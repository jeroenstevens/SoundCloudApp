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

    private final LayoutInflater mLayoutInflater;

    public MusicListAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        // Get the size of the playlist
        return 0;
    }

    @Override
    public Track getItem(int position) {

        // Return track based on position
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // If convertView null
        //// Inflate row
        //// New ViewHolder
        //// Find views in convertview and pass them to the holder
        //// Set the ViewHolder as a tag for the view
        // Else
        //// Get tag from convertView
        // Get a track by getItem()
        // Set the text on the views of the viewHolder
        // Return convertView

        return convertView;
    }

    private class ViewHolder {
        public TextView trackArtist;
        public TextView trackTitle;
    }
}
