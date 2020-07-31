package com.example.alstarapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;


public class SignUpActivity extends AppCompatActivity {
    //We initialize the variable to be able to identify the items in the activity xml
    public static final String TAG = "SignUpActivity";
    private EditText etUserName;
    private EditText etPassword;
    private Button btnSighUp;
    ParseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //connect/ the elements in the activity xml with the signup activity
        if(ParseUser.getCurrentUser() != null){
            gotoLoginActivity();
        }
        etPassword = findViewById(R.id.etPassword);
        etUserName= findViewById(R.id.etUsername);
        btnSighUp = findViewById(R.id.btnSignUp);

        user = new ParseUser();
        //set the user properties


        btnSighUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                signUpUser(username, password);
// Set custom properties
               user.put("phone", "650-253-0000");
                user.setUsername("joestevens");
                user.setPassword("secret123");
                // Set custom properties
                user.put("phone", "650-253-0000");

// Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {

                    }

                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            gotoLoginActivity();
                            finish();;

                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });


            }

        });
    }

    private void signUpUser(String username, String password) {
        ParseUser newUser = new ParseUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    gotoLoginActivity();
                    finish();;

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(SignUpActivity.this, "Oh no! An error occurred.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"Error Here",e);
                }
            }
        });
    }

    private void gotoLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        //we do not want to keep asking the user to login anytime I exit the app without logging out!
        //so that is why we use the finish method
        finish();


    }


}
