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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private TextInputEditText etName, etEmail, etMobile;
    private MaterialButton btnRegister;
    private FirebaseAuth mAuth;

    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etName = view.findViewById(R.id.name);
        etEmail = view.findViewById(R.id.email);
        etMobile = view.findViewById(R.id.mobile);
        btnRegister = view.findViewById(R.id.btn_register);


        mAuth = FirebaseAuth.getInstance();
        mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(false);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, mobileNumber;
                if (etName.getText().toString().trim().equalsIgnoreCase("")) {
                    Snackbar.make(btnRegister, "Please Enter Your Name.", Snackbar.LENGTH_SHORT).show();
                    etName.requestFocus();
                } else if (etEmail.getText().toString().trim().equalsIgnoreCase("")) {
                    Snackbar.make(btnRegister, "Please Enter Your Email.", Snackbar.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                } else if (etMobile.getText().toString().length() != 10) {
                    Snackbar.make(btnRegister, "Please Enter Valid Mobile Number.", Snackbar.LENGTH_SHORT).show();
                    etMobile.requestFocus();
                } else {
                    name = etName.getText().toString().trim();
                    email = etEmail.getText().toString().trim();
                    mobileNumber = "+91" + etMobile.getText().toString().trim();
                    registerUser(name, email, mobileNumber);
                }
            }
        });

        return view;
    }

    private void registerUser(String name, String email, String mobileNumber) {
        Log.i(TAG, "registerUser: Name " + name);
        Log.i(TAG, "registerUser: Email " + email);
        Log.i(TAG, "registerUser: Mobile " + mobileNumber);


        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobileNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Log.i(TAG, "onVerificationCompleted: " + phoneAuthCredential.getSmsCode());

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.i(TAG, "onVerificationFailed: " + e.getMessage());
                                Toast.makeText(getContext(), e.getMessage() + "", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Toast.makeText(getContext(), "Aapka Code Send ho gya hai. ", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onCodeSent: " + s);


                                OtpVerificationFragment fragment = new OtpVerificationFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("token", s);
                                bundle.putString("name", name);
                                bundle.putString("email", email);
                                bundle.putString("mobile", mobileNumber);
                                fragment.setArguments(bundle);


                                Utils.replaceFragment(fragment, getActivity());
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}