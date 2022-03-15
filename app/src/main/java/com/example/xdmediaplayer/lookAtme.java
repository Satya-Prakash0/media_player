package com.example.xdmediaplayer;

import static com.example.xdmediaplayer.VideoFolderAdapter.foldervideofiles;
import static com.example.xdmediaplayer.videoAdapter.videofiles;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.pd.lookatme.LookAtMe;

import java.util.ArrayList;

public class lookAtme extends AppCompatActivity {
    private LookAtMe lookAtMe;
    int position=-1;
    ArrayList<videoFiles> myfiles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_atme);

        lookAtMe = findViewById(R.id.lookme);
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

        lookAtMe.init(this);
        lookAtMe.setVideoURI(Uri.parse(path));
         //lookAtMe.setVideoPath("http://website.com/video/mp4/62000/62792m.mp4");

        lookAtMe.start();
        lookAtMe.setLookMe();
    }
    public void setFullScreenMethod(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        lookAtMe.stopPlayback();
        super.onBackPressed();
    }
}