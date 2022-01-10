package com.example.xdmediaplayer;

import static com.example.xdmediaplayer.secondAcitvity.videofiles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FilesFragment extends Fragment {
RecyclerView recyclerView;
View view;
videoAdapter videoAdapter;
    public FilesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_files, container, false);
        recyclerView=view.findViewById(R.id.filesRV);
        if(videofiles!=null && videofiles.size()>0){
             videoAdapter =new videoAdapter(getContext(),videofiles);
             recyclerView.setAdapter(videoAdapter);
             recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }
        return view;
    }
}