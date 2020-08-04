package com.example.alstarapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

//since the instance review adapter is never used we gonna create an instance of it in the post fragment
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private static final String TAG = "ReviewsAdapter";
    //constructors for taking two parameter
    //one for context
    private Context context;
    //two for the list of reviews
    private List<Review> reviews;
    //let's create a constructor which takes in both of the constructors
    public ReviewsAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //uses the Layout inflater which we are getting from the context and then inflates the layout file we just found
        //this return a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        //let's now wrap the view inside the viewholder
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //we want to get the review located at this position
        //this returns a review
        Review review = reviews.get(position);
        //now what we want to do is take the view holder passed in and bind the data of the post into the viewholder
        holder.bind(review);

    }
    @Override
    public int getItemCount() {
        return reviews.size();
    }

    //clear all the elements of the recycler
    public void clear() {
        reviews.clear();
        notifyDataSetChanged();
    }
    // Add a list of reviews
    public void addAll(List<Review> reviewList) {
        reviews.addAll(reviewList);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        // so we reference inside the viewholder a step from the bind method
        // private ImageView ivProfile;
        private ImageView ivItemPic;
        private TextView tvProfileName;
        private TextView tvItemName;
        private TextView tvItemBrand;
        private TextView tvPrice;
        private TextView tvStore;
        private RatingBar rbratingBar;
        //private ImageView ivMessage;
        private TextView tvDescription;
        private TextView tvPurchaseMode;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //what we need to do in the constructor of the viewholder is to identify the views according to Id inside the item we passsed in
            //ivProfile = itemView.findViewById(R.id.ivProfile);
            ivItemPic = itemView.findViewById(R.id.ivItemPic);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            //tvProfileName = itemView.findViewById(R.id.tvProfileName);
            tvItemBrand = itemView.findViewById(R.id.tvItemBrand);
            rbratingBar = itemView.findViewById(R.id.rbratingBar);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStore = itemView.findViewById(R.id.tvStore);
            //ivMessage= itemView.findViewById(R.id. ivMessage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPurchaseMode = itemView.findViewById(R.id.tvPurchaseMode);
            //when the user clicks on the review it takes them to the details page

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon = getAdapterPosition();
                    Review review = reviews.get(positon);
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra(Review.class.getSimpleName(), review);
                    context.startActivity(i);
                }
            });
        }
        public void bind(Review review) {
            //now we need to bind the data coming from the post into the view elements
            //in order to that we have to access view elements we defined inside item review
            // so we reference inside the viewholder
            //now back in tbe bind method we are filling the data from the post and bind to the view identified in the viewholder constructor
            //tvProfileName.setText(review.getReviewer().getUsername());
            tvDescription.setText(review.getKeyDescription());
            //tvProfileName.setText(review.getReviewer().getUsername());//error compelled me to delete the Parse user variable inside the data
            tvItemBrand.setText(review.getKeyBrand());
            tvPrice.setInputType(review.getPrice());
            tvPurchaseMode.setText(review.getKeyPurchaseMode());
            tvStore.setText(review.getStoreName());
            rbratingBar.setRating(review.getItemCount());
            tvItemName.setText(review.getKeyItemName());
            ParseFile file = review.getImage();
            if (file == null) {
                Log.e(TAG, "The image is null");
            } else {
                Glide.with(context).load(review.getImage().getUrl()).into(ivItemPic);
            }


        }
    }


}




