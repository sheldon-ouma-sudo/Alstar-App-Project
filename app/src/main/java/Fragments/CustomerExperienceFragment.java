package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alstarapp.HomeScreenActivity;
import com.example.alstarapp.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;


public class CustomerExperienceFragment extends Fragment {
    public static final String TAG = "CustomerExperienceFrag";
    private TextView tvCustomerExperience;
    private TextView etConcern;
    private Button btnFinish;


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
        etConcern = view.findViewById(R.id.etConcern);
        btnFinish = view.findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            @Override
            public void onClick(View view) {
                String description = etConcern.getText().toString();
                saveCustomerExperience(description);
                fragmentManager.beginTransaction().replace(R.id.flContainer, new SubmitFragment()).commit();

            }
        });


    }

    private void saveCustomerExperience(String description) {
        ((HomeScreenActivity) getActivity()).review.setKeyDescription(description);
        ((HomeScreenActivity) getActivity()).review.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    //there must be an error while saving the data in the backend
                Log.e(TAG, "Error while saving in the backend");
                }
                //Otherwise the review was saved successfully
                Log.i(TAG, "Review data was saved succesfully");


            }


        });
    }
}