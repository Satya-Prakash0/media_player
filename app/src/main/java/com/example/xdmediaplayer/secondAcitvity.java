package com.example.xdmediaplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.nio.file.Path;
import java.util.ArrayList;

public class secondAcitvity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISION = 123;
    BottomNavigationView bottomNavigationView;
    static ArrayList<videoFiles> videofiles=new ArrayList<>();
    static ArrayList<String> folderList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acitvity);
        permission();
       bottomNavigationView=findViewById(R.id.bottomNavView);
       bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()) {
                   case R.id.folder:
                       //Toast.makeText(getApplicationContext(), "Folder", Toast.LENGTH_SHORT).show();
                       //item.setChecked(true);
                       FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                       fragmentTransaction.replace(R.id.mainFragment,new FolderFragment());
                       fragmentTransaction.commit();
                       break;

                   case R.id.filter:
                      // Toast.makeText(getApplicationContext(), "Filter", Toast.LENGTH_SHORT).show();
                      // item.setChecked(true);
                       FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                       fragmentTransaction2.replace(R.id.mainFragment,new FilesFragment());
                       fragmentTransaction2.commit();
                       break;
               }
               return false;
           }
       });
    }

    private void permission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(secondAcitvity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISION);
        }
        else{
           // Toast.makeText(secondAcitvity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            videofiles=getAllvideos(this);
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFragment,new FolderFragment());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_PERMISION){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(secondAcitvity.this, "Permission successfull", Toast.LENGTH_SHORT).show();
                videofiles=getAllvideos(this);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFragment,new FolderFragment());
                fragmentTransaction.commit();
            }
            else{
                ActivityCompat.requestPermissions(secondAcitvity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISION);
            }
        }
    }
    public ArrayList<videoFiles> getAllvideos(Context context){
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
     Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);
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
             //Log.e("Path",path);
             // /storage/emulated/0/WhatsApp/Media/WhatsApp Video/VID-20211211-WA0009.mp4
             int slashFirstIndex=path.lastIndexOf("/");
             // /storage/emulated/0/WhatsApp/Media/WhatsApp Video
             String subString=path.substring(0,slashFirstIndex);
             int index=subString.lastIndexOf("/");
             String folderName=subString.substring(index+1,slashFirstIndex);
             tempvideofiles.add(videofiles);
             if(!folderList.contains(folderName))
                 folderList.add(folderName);
         }
         cursor.close();
         }
     return tempvideofiles;
     }
}
