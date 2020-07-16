package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alstarapp.R;


public class ProfileFragment extends Fragment {
    private ImageView ivProfile;
    private TextView tvProfile;
    private TextView tvMessages;
    private TextView tvMyReviews;
    private Button btnLogout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);



    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        ivProfile= view.findViewById(R.id.ivProfile);
        tvProfile = view.findViewById(R.id.tvProfile);
        tvMessages = view.findViewById(R.id.tvMessages);
        tvMyReviews = view.findViewById(R.id.tvMyReviews);
        btnLogout = view.findViewById(R.id.btnLogout);
    }



}





