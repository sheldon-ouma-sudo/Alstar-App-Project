package com.example.alstarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreenActivity extends AppCompatActivity {
    //Declare variable to enable connection to the activitiy xml
    private TextView tvWelcome;
    private TextView tvTrends;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

     tvWelcome = findViewById(R.id.tvWelcome);
     tvTrends = findViewById(R.id.tvTrends);
     bottomNavigation = findViewById(R.id.bottom_navigation);

        //when the user clicks on the bars on the navigation bar he is navigated to the corresponding screend
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // we are doing nothing here since we are on the home screen already
                        return true;
                    case R.id.action_search:
                        // we navigate to the search screen
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        // we navigate to the profile screen
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