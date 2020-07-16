package com.example.alstarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.ComposeFragment;
import Fragments.HomeScreenFragment;
import Fragments.ProfileFragment;
import Fragments.SearchHomeFragment;

public class HomeScreenActivity extends AppCompatActivity {
    //Declare variable to enable connection to the activitiy xml
    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        bottomNavigation = findViewById(R.id.bottom_navigation);

        //when the user clicks on the bars on the navigation bar he is navigated to the corresponding screend
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // we are doing nothing here since we are on the home screen already
                        fragment = new HomeScreenFragment();
                        break;
                    case R.id.action_search:
                        // we navigate to the search screen
                      fragment = new SearchHomeFragment();
                      break;
                    case R.id.action_profile:
                        // we navigate to the profile screen
                        fragment = new ProfileFragment();
                       break;
                    case R.id.action_compose:
                        // we navigate to add review screen/Compose Screen
                     fragment = new ComposeFragment();
                     default:
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        //set the default selection
        bottomNavigation.setSelectedItemId(R.id.action_home);
    }

    }




