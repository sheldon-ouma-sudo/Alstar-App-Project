    package com.example.alstarapp;

    import android.content.Context;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.RatingBar;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.parse.ParseFile;

    import java.util.List;

    public class myReviewsAdapter extends Fragment {


        //since the instance review adapter is never used we gonna create an instance of it in the post fragment
        public class MyReviewsAdapter extends RecyclerView.Adapter<MyReviewsAdapter.ViewHolder> {
            private static final String TAG = "MyReviewsAdapter";
            //constructors for taking two parameter
            //one for context
            private Context context;
            //two for the list of reviews
            private List<Review> myReviews;
            //let's create a constructor which takes in both of the constructors


            public MyReviewsAdapter(Context context, List<Review> reviews) {
                this.context = context;
                this.myReviews = reviews;
            }

            @NonNull
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //uses the Layout inflater which we are getting from the context and then inflates the layout file we just found
                //this return a view
                View view = LayoutInflater.from(context).inflate(R.layout.item_myreview, parent, false);
                //let's now wrap the view inside the viewholder
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                //we want to get the review located at this position
                //this returns a review
                Review review = myReviews.get(position);
                //now what we want to do is take the view holder passed in and bind the data of the post into the viewholder
                holder.bind(review);

            }

            @Override
            public int getItemCount() {
                return myReviews.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder {
                // so we reference inside the viewholder a step from the bind method
                private ImageView ivItemPic;

                private TextView tvMyItemName;
                private TextView tvMyItemBrand;
                private TextView tvMyPrice;
                private TextView tvMyStore;
                private RatingBar rbMyratingBar;
                private TextView tvMyDescription;
                private TextView tvMyPurchaseMode;
                private ImageView  ivMyItemPic;


                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    //what we need to do in the constructor of the viewholder is to identify the views according to Id inside the item we passsed in

                    ivMyItemPic = itemView.findViewById(R.id.ivMyItemPic);
                    tvMyItemName = itemView.findViewById(R.id.tvMyItemName);
                    tvMyItemBrand = itemView.findViewById(R.id.tvMyItemBrand);
                    rbMyratingBar = itemView.findViewById(R.id.rbMyratingBar);
                    tvMyPrice = itemView.findViewById(R.id.tvMyPrice);
                    tvMyStore = itemView.findViewById(R.id.tvMyStore);
                    tvMyDescription = itemView.findViewById(R.id.tvMyDescription);
                    tvMyPurchaseMode = itemView.findViewById(R.id.tvMyPurchaseMode);

                }

                public void bind(Review review) {
                    //now we need to bind the data coming from the post into the view elements
                    //in order to that we have to access view elements we defined inside item review
                    // so we reference inside the viewholder
                    //now back in tbe bind method we are filling the data from the post and bind to the view identified in the viewholder constructor
                    //tvProfileName.setText(review.getReviewer().getUsername());
                    tvMyDescription.setText(review.getKeyDescription());
                    tvMyItemBrand.setText(review.getKeyBrand());
                    tvMyPrice.setInputType(review.getPrice());
                    tvMyPurchaseMode.setText(review.getKeyPurchaseMode());
                    tvMyStore.setText(review.getStoreName());
                    rbMyratingBar.setRating(review.getItemCount());
                    tvMyItemName.setText(review.getKeyItemName());
                    ParseFile file = review.getImage();
                    if (file == null) {
                        Log.e(TAG, "The image is null");
                    } else {
                        Glide.with(context).load(review.getImage().getUrl()).into(ivMyItemPic);
                    }


                }
            }
        }
    }

























