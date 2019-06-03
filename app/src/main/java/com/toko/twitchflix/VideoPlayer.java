package com.toko.twitchflix;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        String movieID;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                movieID = null;
            } else {
                movieID = extras.getString("movieID");
            }
        } else {
            movieID = (String) savedInstanceState.getSerializable("movieID");
        }

        String url = "http://" + Server.getIP() + ":" + Server.getPortMovies() + "/movies/" + movieID + ".mp4";

        videoView = (VideoView) findViewById(R.id.videoViewId);

        mediaController = new MediaController(this);

        videoView.setVideoURI(Uri.parse(url));

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.setZOrderOnTop(true);

        videoView.start();
    }

}
