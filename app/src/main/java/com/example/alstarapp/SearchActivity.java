package com.example.alstarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
    private TextView tvOptions;
    private TextView tvSearch;
    private TextView tvSearchOptions;
    private Button btnSearch;
    private BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        tvOptions = findViewById(R.id.tvOptions);
        tvSearch = findViewById(R.id.tvSearch);
        tvSearchOptions = findViewById(R.id.tvSearchOptions);
        btnSearch = findViewById(R.id.btnSearch);
        bottom_navigation= findViewById(R.id.bottom_navigation);



        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // we navigate to the home screen
                        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_search:
                        // we navigate to the search screen
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
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