package com.example.flowersvalley.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flowersvalley.R;
import com.example.flowersvalley.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FlowerDetailFragment extends Fragment {
    private static final String TAG = "FlowerDetailFragment";
    String flowerId;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public FlowerDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flowerId = getArguments().getString("flower_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flower_detail, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        if (flowerId != null) {
            databaseReference = firebaseDatabase.getReference("flowers").child(flowerId);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.i(TAG, "onDataChange: " + snapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "onCancelled: " + error);
                }

            });
        } else {
            Toast.makeText(getContext(), "Please try again.", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}