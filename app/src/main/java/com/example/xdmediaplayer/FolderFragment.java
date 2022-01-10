package com.example.xdmediaplayer;

import static com.example.xdmediaplayer.secondAcitvity.folderList;
import static com.example.xdmediaplayer.secondAcitvity.videofiles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FolderFragment extends Fragment {
      folderAdapter folderAdapter;
      RecyclerView recyclerView;
    public FolderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_folder, container, false);
        recyclerView =view.findViewById(R.id.folderRV);
        if(folderList!=null && folderList.size()>0 && videofiles!=null){
            folderAdapter=new folderAdapter(folderList,videofiles,getContext());
            recyclerView.setAdapter(folderAdapter);
           // Log.e(String.valueOf(folderList), String.valueOf(folderList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }
        return view;
    }
}