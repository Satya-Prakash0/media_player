package com.example.xdmediaplayer;

import static com.example.xdmediaplayer.VideoFolderAdapter.foldervideofiles;
import static com.example.xdmediaplayer.videoAdapter.videofiles;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    static PlayerView playerView;
   static  SimpleExoPlayer simpleExoPlayer;
    int position = -1;
    ArrayList<videoFiles> myfiles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setFullScreenMethod();
        setContentView(R.layout.activity_player);
        playerView = findViewById(R.id.exoplayer_movie);
        position = getIntent().getIntExtra("position", -1);
        String sender=getIntent().getStringExtra("sender");
        if(sender.equals("FolderIsSending")){
             myfiles=foldervideofiles;
        }
        else{
            myfiles=videofiles;
        }
       getSupportActionBar().hide();
        String path = myfiles.get(position).getPath();
        //simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        if (simpleExoPlayer!=null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
        }
            if (path != null) {
                Uri uri = Uri.parse(path);
                simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
                // if(simpleExoPlayer.isPlaying()) simpleExoPlayer.stop();
                DataSource.Factory factory = new DefaultDataSourceFactory(this,
                        Util.getUserAgent(this, "My App Name"));
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new ProgressiveMediaSource.Factory(factory, extractorsFactory).createMediaSource(uri);
                playerView.setPlayer(simpleExoPlayer);
                playerView.setKeepScreenOn(true);
                //if(simpleExoPlayer.isPlaying()) simpleExoPlayer.stop();
                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(true);

            }

    }
    public void setFullScreenMethod(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        simpleExoPlayer.stop();
        super.onBackPressed();
    }
}
