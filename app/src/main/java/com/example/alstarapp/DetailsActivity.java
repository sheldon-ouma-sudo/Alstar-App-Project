package com.example.alstarapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        ImageView ivItemImage = findViewById(R.id.ivItemImage);
        TextView tvUsername = findViewById(R.id.tvUserName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView  tvProductName= findViewById(R.id.tvProductName);
        TextView  tvPurchaseMode = findViewById(R.id.tvPurchaseMode);
        TextView  tvStoreName = findViewById(R.id.tvStoreName);
        TextView  tvPrice = findViewById(R.id.tvPrice);
        ImageView ivProfileImage= findViewById(R.id.ivProfileImage);
        TextView  tvUserName = findViewById(R.id.tvUserName);
        Button  btnPurchase = findViewById(R.id.btnPurchase);
        Button btnMessage = findViewById(R.id.btnMessage);

        review = getIntent().getParcelableExtra(Review.class.getSimpleName());

        Glide.with(this).load(review.getImage().getUrl()).into(ivItemImage);
        tvUsername.setText(review.getReviewer().getUsername());
        tvDescription.setText(review.getKeyDescription());
        ratingBar.setRating(review.getItemCount());
        tvProductName.setText(review.getKeyItemName());
        tvPurchaseMode.setText(review.getKeyPurchaseMode());
        tvStoreName.setText(review.getStoreName());
        tvPrice.setInputType(review.getPrice());


        // clicked on the message button takes you to message sending page
        btnMessage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // Intent i = new Intent(this, MessageBoardActivity.class);

              //startActivity(i);
          }
      });
        // clicked on the purchase button navigates user to purchase store
      btnPurchase.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Intent.ACTION_SEARCH);

// Build the intent
              Uri productStore = Uri.parse("http://www.amazon.com/gp/mas/dl/android?");
              Intent productStoreIntent = new Intent(Intent.ACTION_VIEW, productStore);

// Verify it resolves
              PackageManager packageManager = getPackageManager();
              List<ResolveInfo> activities = packageManager.queryIntentActivities(productStoreIntent, 0);
              boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
              if (isIntentSafe) {
                  startActivity(productStoreIntent);


              }
          }
      });
    };
}





