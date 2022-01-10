package com.example.xdmediaplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class folderAdapter extends RecyclerView.Adapter<folderAdapter.MyHolder> {
    private ArrayList<String> folderName;
    private ArrayList<videoFiles> videofiles;
    private Context mContext;

    public folderAdapter(ArrayList<String> folderName, ArrayList<videoFiles> videofiles, Context mContext) {
        this.folderName = folderName;
        this.videofiles = videofiles;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.folder_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
     holder.folderName.setText(folderName.get(position));
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =new Intent(mContext,VideoFolderActivity.class);
             intent.putExtra("folderName", folderName.get(position).toString());
              mContext.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return folderName.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView folderName;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            folderName=itemView.findViewById(R.id.folder_name);
        }
    }
}
