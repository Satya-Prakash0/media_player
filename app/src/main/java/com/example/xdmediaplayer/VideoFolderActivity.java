package com.example.xdmediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class VideoFolderActivity extends AppCompatActivity {
      RecyclerView recyclerView;
      VideoFolderAdapter videoFolderAdapter;
      String myFolderName;
      ArrayList<videoFiles> videoFilesArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_folder);
        recyclerView =findViewById(R.id.FolderVideoRV);
        myFolderName=getIntent().getStringExtra("folderName");

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        if(myFolderName!=null){
            videoFilesArrayList=getAllvideos(this,myFolderName);
        }
      //  Toast.makeText(this, videoFilesArrayList.size(), Toast.LENGTH_SHORT).show();
        if(videoFilesArrayList.size()>0){
            videoFolderAdapter=new VideoFolderAdapter(this,videoFilesArrayList);
          //  recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            //recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(videoFolderAdapter);
        }
    }
    public ArrayList<videoFiles> getAllvideos(Context context,String FolderName){
        ArrayList<videoFiles> tempvideofiles=new ArrayList<>();
        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection={
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME
        };

        String selection =MediaStore.Video.Media.DATA + " like?";
        String[] selectionArgs= new String[]{"%/" +FolderName + "/%"};
        Cursor cursor=context.getContentResolver().query(uri,projection,selection,selectionArgs,null);
        if(cursor!=null){
            while(cursor.moveToNext()) {
                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String title = cursor.getString(2);
                String filename = cursor.getString(3);
                String size = cursor.getString(4);
                String dateAdded = cursor.getString(5);
                String duration = cursor.getString(6);
                videoFiles videofiles = new videoFiles(id, path, filename, duration, size, dateAdded, title);
                Log.e("Path",path);
                tempvideofiles.add(videofiles);
            }
            cursor.close();
           // Log.e(String.valueOf(tempvideofiles),"videoFilesArrayList" );
        }
        return tempvideofiles;
    }
}