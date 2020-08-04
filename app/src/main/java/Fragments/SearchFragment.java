package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alstarapp.R;
import com.example.alstarapp.Review;
import com.example.alstarapp.ReviewsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private SearchView svSearch;
    RecyclerView rvReviews;

    //let's create the array list and its adapter
    ArrayList<Review> reviewArrayList;
    ReviewsAdapter reviewAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        svSearch = view.findViewById(R.id.svSearch);
        rvReviews = view.findViewById(R.id.rvReviews);
        //let's get all the reviews from the backend and add them to the reviewArrayList
        //using api presented by the parse in order get data out of database
        reviewArrayList = new ArrayList<Review>();

        reviewAdapter = new ReviewsAdapter(getContext(), reviewArrayList);
        rvReviews.setAdapter(reviewAdapter);
        rvReviews.setLayoutManager(new LinearLayoutManager(getContext()));

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                queryReview(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }


    private void queryReview(String searchQuery) {
        // Specify which class to query
        ParseQuery<Review> query = ParseQuery.getQuery(Review.class);
        query.include(Review.KEY_REVIEWER);
        query.whereContains(Review.KEY_ITEM_NAME, searchQuery);
        //retrieve all the reviews in background
        query.findInBackground(new FindCallback<Review>() {
            @Override
            public void done(List<Review> reviewList, ParseException e) {
                   //add the reviews to the list and notify changes

                reviewArrayList.addAll(reviewList);
                reviewAdapter.notifyDataSetChanged();
            }

        });
    }


}