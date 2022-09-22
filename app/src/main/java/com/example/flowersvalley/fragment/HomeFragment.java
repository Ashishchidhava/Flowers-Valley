package com.example.flowersvalley.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.flowersvalley.MainActivity;
import com.example.flowersvalley.R;
import com.example.flowersvalley.adapter.FlowerAdapter;
import com.example.flowersvalley.model.Flower;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    RecyclerView flowerRecyclerview;
    ArrayList<Flower> flowers;
    AppCompatTextView viewAll;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.image_slider);
        flowerRecyclerview = view.findViewById(R.id.flower_recyclerview);
        viewAll = view.findViewById(R.id.view_all);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel("https://media.istockphoto.com/photos/panoramic-view-of-mt-cook-mountain-range-at-colorful-sunset-picture-id642047430?s=612x612", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://images.prismic.io/indiahike/226b9030-85aa-4032-a0a0-1562d697ff53_ValleyOfFlowers+-+Pavan+Jain+-+Blooming+flowers+of+the+valley.jpg?auto=compress,format", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://bit.ly/3fLJf72", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyZOUuvPlPeUIKSU3Bg9F4A7IGWkvLBcSUMQ&usqp=CAU", ScaleTypes.FIT));

        imageSlider.setImageList(imageList);


        flowerRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        flowers = new ArrayList<>();
        flowers.add(new Flower("Angle", 399, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Angle", 199, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Chameli", 299, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Kamal", 599, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));
        flowers.add(new Flower("Angle", 99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDDW0EkVedjx6cwPVkqKXAp9iRtq75WTBh3DhHWvWC&s"));


        flowerRecyclerview.setAdapter(new FlowerAdapter(flowers, getContext()));
















        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, new ViewAllFragment());
                ft.commit();
            }
        });


        return view;
    }

}