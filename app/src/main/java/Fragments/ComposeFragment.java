package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alstarapp.R;



public class ComposeFragment extends Fragment {
    private TextView tvNewReview;
    private EditText etProductType;
    private EditText  etBrandName;
    private EditText  etItemName;
    private EditText etPurchaseStore;
    private EditText etPurchaseMode;
    private EditText etPrice;
    private EditText btnnext;



    public ComposeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        tvNewReview = view.findViewById(R.id.tvNewReview);
        etBrandName= view.findViewById(R.id. etBrandName);
        etItemName = view.findViewById(R.id. etItemName);
        etPurchaseStore = view.findViewById(R.id. etPurchaseStore );
        etPrice = view.findViewById(R.id.etPrice);
        btnnext= view.findViewById(R.id.btnext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Fragment fragment = new RateReviewFragment();

            }
         //fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
           // return true;

        });


    }


    }



