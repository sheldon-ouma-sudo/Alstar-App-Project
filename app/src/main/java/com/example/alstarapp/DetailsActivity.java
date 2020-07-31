package com.example.alstarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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




        btnMessage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      btnPurchase.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Intent.ACTION_SEARCH);


// Always use string resources for UI text.
// This says something like "Share this photo with"
              String title = getResources().getString(R.string.app_name);
// Create intent to show chooser
              Intent chooser = Intent.createChooser(intent, title);

// Verify the intent will resolve to at least one activity
              if (intent.resolveActivity(getPackageManager()) != null) {
                  startActivity(chooser);
              }
          }
      });

    }

}
