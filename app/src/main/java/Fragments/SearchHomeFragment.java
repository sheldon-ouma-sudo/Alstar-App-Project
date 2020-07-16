package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alstarapp.R;


public class SearchHomeFragment extends Fragment {
    private TextView tvOptions;
    private TextView tvSearch;
    private TextView tvSearchOptions;
    private Button btnSearch;




    public SearchHomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_home, container, false);
    }
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        tvOptions = view.findViewById(R.id. tvOptions);
        tvSearch = view.findViewById(R.id. tvSearch);
        tvSearchOptions = view.findViewById(R.id.tvSearchOptions);
        btnSearch = view.findViewById(R.id.btnSearch);
    }



}