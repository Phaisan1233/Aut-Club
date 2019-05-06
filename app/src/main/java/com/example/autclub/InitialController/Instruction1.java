package com.example.autclub.InitialController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.autclub.R;


public class Instruction1 extends Fragment {
    public Instruction1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instruction1, container, false);
//     ImageView imageview = (ImageView)rootView;
//        imageview.setImageResource(R.drawable.calenderins);
//       imageview.setTag(R.drawable.calenderins);
        return rootView;
    }


}
