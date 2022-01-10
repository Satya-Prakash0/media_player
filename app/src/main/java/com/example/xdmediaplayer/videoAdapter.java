package com.example.xdmediaplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
public class videoAdapter extends RecyclerView.Adapter<videoAdapter.MyViewHolder>{
    private Context mContext;
    static ArrayList<videoFiles> videofiles;
    View view;
    public videoAdapter(Context mContext, ArrayList<videoFiles> videofiles) {
        this.mContext = mContext;
        this.videofiles = videofiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.video_filename.setText(videofiles.get(position).getTitle());
        Glide.with(mContext)
                .load(new File(videofiles.get(position).getPath()))
                .into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PlayerActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("sender","filesIsSending");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videofiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail,menu_more;
        TextView video_duration,video_filename;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.thumbnail);
            menu_more=itemView.findViewById(R.id.menu_more);
            video_duration=itemView.findViewById(R.id.video_duration);
            video_filename=itemView.findViewById(R.id.video_filename);
        }
    }
}
