package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alstarapp.R;


public class CustomerExperienceFragment extends Fragment {
    private TextView tvCustomerExperience;
    private TextView  tvConcern;
    private Button  btnFinish;


    public CustomerExperienceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_experience, container, false);




    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        tvCustomerExperience = view.findViewById(R.id.tvCustomerExperience);
        tvConcern = view.findViewById(R.id.tvConcern);
        btnFinish = view.findViewById(R.id.btnFinish);
    }



}