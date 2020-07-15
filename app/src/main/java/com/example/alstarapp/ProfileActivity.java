package com.example.alstarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private ImageView ivProfile;
    private TextView tvProfile;
    private TextView tvMessages;
    private TextView tvMyReviews;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ivProfile = findViewById(R.id.ivProfile);
        tvProfile = findViewById(R.id.tvProfile);
        tvMessages= findViewById(R.id.tvMessages);
        tvMyReviews = findViewById(R.id.tvMyReviews);
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        //when the user clicks on the bars on the navigation bar he is navigated to the corresponding screend
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // we navigate to the home screen
                        Intent intent = new Intent(getApplicationContext(),HomeScreenActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_search:
                        // we navigate to the search screen
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        // we navigate do nothing here since we are on the profile screen
                        intent = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_compose:
                        // we navigate to add review screen/Compose Screen
                        intent = new Intent(getApplicationContext(),ComposeActivity.class);
                        startActivity(intent);
                        return true;
                    default: return true;
                }
            }
        });








    }
}