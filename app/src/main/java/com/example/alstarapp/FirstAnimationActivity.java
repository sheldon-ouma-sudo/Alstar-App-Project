package com.example.alstarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class FirstAnimationActivity extends AppCompatActivity {
    ViewFlipper firstViewFlipper;
    ViewFlipper secondViewFlipper;
    ViewFlipper thirdViewFlipper;
    ViewFlipper lastViewFlipper;
    TextView tvElectronics;
    TextView tvClothes;
    TextView tvFoodAndAccesories;
    TextView tvManyMore;
    TextView tvSeeTrendingReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_animation);

        tvElectronics = findViewById(R.id.tvElectronics);
        tvClothes = findViewById(R.id.tvClothing);
        tvFoodAndAccesories = findViewById(R.id.tvFoodAndAccesories);
        tvManyMore = findViewById(R.id.tvOthers);
        tvSeeTrendingReview = findViewById(R.id.tvSeeTrending);

        tvSeeTrendingReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomePageActivity();
                //we do not want to keep asking the user to login anytime I exit the app without logging out!
                //so that is why we use the finish method


            }
        });


        int[] images = {R.drawable.electronics, R.drawable.electronics2, R.drawable.electronics3};
        firstViewFlipper = findViewById(R.id.firstViewFlipper);

        for (int i = 0; i < images.length; i++) {
            flipperImages(images[i]);


        }

        int[] imagesSecond = {R.drawable.food, R.drawable.food2, R.drawable.food3};
        secondViewFlipper = findViewById(R.id.SecondViewFlipper);
        for (int i = 0; i < images.length; i++) {
            flipperSecondImages(imagesSecond[i]);


        }

        int[] imagesThird = {R.drawable.clothing, R.drawable.clothing2, R.drawable.clothings3};
        thirdViewFlipper = findViewById(R.id.ThirdViewFlipper);
        for (int i = 0; i < images.length; i++) {
            flipperThirdImages(imagesThird[i]);


        }
        int[] imagesLast = {R.drawable.others, R.drawable.others2, R.drawable.others3};
        lastViewFlipper = findViewById(R.id.FourthViewFlipper);
        for (int i = 0; i < images.length; i++) {
            flipperLastImages(imagesLast[i]);

        }
    }

    private void goToHomePageActivity() {
        Intent i = new Intent(this, HomePageActivity.class);
        startActivity(i);
    }

    private void flipperThirdImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        thirdViewFlipper.addView(imageView);
        thirdViewFlipper.setFlipInterval(4200);
        thirdViewFlipper.setAutoStart(true);
        thirdViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        thirdViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void flipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        firstViewFlipper.addView(imageView);
        firstViewFlipper.setFlipInterval(4500);
        firstViewFlipper.setAutoStart(true);
        firstViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        firstViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    private void flipperSecondImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        secondViewFlipper.addView(imageView);
        secondViewFlipper.setFlipInterval(4000);
        secondViewFlipper.setAutoStart(true);
        secondViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        secondViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    private void flipperLastImages(int i) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(i);
        lastViewFlipper.addView(imageView);
        lastViewFlipper.setFlipInterval(4400);
        lastViewFlipper.setAutoStart(true);
        lastViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        lastViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
/*int imagesSecond[] = {R.drawable.clothing,R.drawable.clothing2,R.drawable.clothings3};
        secondViewFlipper = findViewById(R.id.SecondViewFlipper);

        int imagesThird[] = {R.drawable.food,R.drawable.food2,R.drawable.food3};
        thirdViewFlipper = findViewById(R.id.ThirdViewFlipper);
        int imagesLast[] = {R.drawable.others,R.drawable.others2,R.drawable.others3};
        lastViewFlipper = findViewById(R.id.FourthViewFlipper);

/*
        for (int i=0; i<images.length; i++) {
            flipperImages(imagesSecond[i]);
        }
            for (int i=0; i<images.length; i++) {
                flipperImages(images[i]);

                for (int i=0; i<images.length; i++) {
                    flipperImages(imagesThird[i]);
                     public void flipperImages(int images){
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(images);
                firstViewFlipper.addView(imageView);
                firstViewFlipper.setFlipInterval(4000);
                firstViewFlipper.setAutoStart(true);
                firstViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
                firstViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right );
            }
            public void flipperImages(int images){
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(images);
                firstViewFlipper.addView(imageView);
                firstViewFlipper.setFlipInterval(4000);
                firstViewFlipper.setAutoStart(true);
                firstViewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
                firstViewFlipper.setOutAnimation(this, android.R.anim.slide_out_right );
            }
 */


