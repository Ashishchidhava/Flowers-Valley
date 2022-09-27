package com.example.flowersvalley.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.flowersvalley.MainActivity;
import com.example.flowersvalley.R;
import com.example.flowersvalley.SharedPreferenceManager;
import com.example.flowersvalley.adapter.FlowerAdapter;
import com.example.flowersvalley.model.Banner;
import com.example.flowersvalley.model.Flower;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ImageSlider imageSlider;
    private RecyclerView flowerRecyclerview;
    private ArrayList<Flower> flowers;
    private AppCompatTextView viewAll;
    private SharedPreferenceManager preferenceManager;
    private static final String TAG = "HomeFragment";
    private DatabaseReference mDatabaseRef;
    private Banner banner;

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

        preferenceManager = new SharedPreferenceManager(getContext());
        ArrayList<SlideModel> imageList = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("banners");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    banner = postSnapshot.getValue(Banner.class);
                    Log.i(TAG, "onCreateView: Data > " + postSnapshot.getValue());
                    imageList.add(new SlideModel("" + banner.getImageUrl(),  ScaleTypes.FIT));
                }
                imageSlider.setImageList(imageList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


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