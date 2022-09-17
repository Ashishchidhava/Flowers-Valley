package com.example.flowersvalley.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.flowersvalley.R;
import com.example.flowersvalley.adapter.FlowerAdapter;
import com.example.flowersvalley.adapter.ViewAllFlowerAdapter;
import com.example.flowersvalley.model.Flower;

import java.util.ArrayList;

public class ViewAllFragment extends Fragment {
    RecyclerView flowerRecyclerview;
    ArrayList<Flower> flowers;

    public ViewAllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        flowerRecyclerview = view.findViewById(R.id.flower_recyclerview);


        flowerRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        flowers = new ArrayList<>();
        flowers.add(new Flower("Angle", 399, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Angle", 199, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Chameli", 299, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Kamal", 599, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Angle", 99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));

        flowerRecyclerview.setAdapter(new ViewAllFlowerAdapter(flowers, getContext()));





        return view;
    }
}