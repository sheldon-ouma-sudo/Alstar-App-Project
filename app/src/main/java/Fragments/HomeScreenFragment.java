package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.alstarapp.QuickSort;
import com.example.alstarapp.R;
import com.example.alstarapp.Review;
import com.example.alstarapp.ReviewsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomeScreenFragment extends Fragment {
    private static final String TAG = "HomeScreenFragment";
    //we referenced the review adapter here because it was not being used
    protected TextView tvWelcome;
    protected TextView tvTrends;
    protected RecyclerView rvTrendingReviews;
    protected ReviewsAdapter adapter;
    protected List<Review> allReviews;
    SwipeRefreshLayout swipeContainer;

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homescreen, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvTrends = view.findViewById(R.id.tvTrends);
        rvTrendingReviews = view.findViewById(R.id.rvTrendingReview);
        //let's do step number 2 and we will pass in the two variables the context and a list of reviews
        //let's first initialize the list to ensure it is an empty list
        allReviews = new ArrayList<>();
        adapter = new ReviewsAdapter(getContext(), allReviews);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // our code to refresh the list here.
                // Make sure our call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryReview();
            }
        });

        //set the adapter on the recycler view
        rvTrendingReviews.setAdapter(adapter);
        //  set out the layout manager on the recycler view
        rvTrendingReviews.setLayoutManager(new LinearLayoutManager(getContext()));

                /*
            Steps to use the recycler view:
            1. Create the layout for one row of the list
             2. create the adapter
             3. create the data source
             4. set the adapter on the recycler view

             5. set out the layout manager on the recycler view
             */
        queryReview();
    }

    //using api presented by the parse in order get data out of database
    protected void queryReview() {
        // Specify which class to query
        ParseQuery<Review> query = ParseQuery.getQuery(Review.class);
        query.include(Review.KEY_REVIEWER);
        //retrieve all the reviews in background
        //query.addDescendingOrder(Review.KEY_RATING);
        query.findInBackground(new FindCallback<Review>() {
            @Override
            public void done(List<Review> reviewList, ParseException e) {
                //if the exception is null the data is populated properly
                //iterate through the reviews and log something
                List<Review> sortedList = QuickSort.QuickSort(reviewList, reviewList.size());
                Collections.reverse(sortedList);
                System.out.println("sorted are");
                System.out.println(sortedList.toString());
                //let's clear everything from the adapter first
                adapter.clear();

                //let us instead combine the two together with the help of the helper function
                adapter.addAll(sortedList);
                //allReviews.addAll(reviewList);
                //adapter.notifyDataSetChanged();
                //after finishing refreshing we want to signal the refreshing is done
                swipeContainer.setRefreshing(false);
            }

        });

    }


}

