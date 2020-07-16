package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.alstarapp.R;


public class RateReviewFragment extends Fragment {
    private TextView tvCaptureImage;
    private ImageView ivItemPicture;
    private TextView  tvRate;
    private RatingBar rbItemReview;
    private Button btnNext;

    public RateReviewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate_review, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        tvCaptureImage = view.findViewById(R.id. tvCaptureImage);
        ivItemPicture = view.findViewById(R.id. ivItemPicture);
        tvRate = view.findViewById(R.id.tvRate);
        rbItemReview = view.findViewById(R.id.rbItemReview);
        btnNext = view.findViewById(R.id.btnNext);
    }




}