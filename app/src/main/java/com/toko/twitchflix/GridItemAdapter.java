package com.toko.twitchflix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GridItemAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<String> movieTitles;
    private final ArrayList<String> movieIDs;

    public GridItemAdapter(Context context, ArrayList<String> movieTitles, ArrayList<String> movieIDs) {
        this.context = context;
        this.movieTitles = movieTitles;
        this.movieIDs = movieIDs;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        final int pos = position;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item_layout, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(movieTitles.get(position));

            // set image based on selected text
            final ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = movieTitles.get(position);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground (Void ... params) {
                    try {
                        String url = "http://" + Server.getIP() + ":" + Server.getPortMovies() + "/images/" + movieIDs.get(pos) + ".jpg";
                        Bitmap mIcon11 = null;

                        InputStream in = new java.net.URL(url).openStream();
                        mIcon11 = BitmapFactory.decodeStream(in);
                        imageView.setImageBitmap(mIcon11);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return movieTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}