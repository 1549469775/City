package com.example.jhon.venue.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.R;

/**
 * Created by John on 2017/3/27.
 */

public class MapFragment extends Fragment {

    private View view=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.map_fragment, container,false);
        }
        return view;
    }

    public static MapFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
