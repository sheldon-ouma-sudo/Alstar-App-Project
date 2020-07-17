package com.example.alstarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etLoginPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Let us check if someone is already signed in so that we do not have to login again
            if(ParseUser.getCurrentUser() != null){
                goHomescreenActivity();
            }
//referencing the items on the activity xml
        etUsername = findViewById(R.id.etUserName);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);

//This method Invokes an action when the user clicks on the button when the user clicks on it
        btnLogin.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Log.i(TAG, "onClick login button");
        String username = etUsername.getText().toString();
        String password = etLoginPassword.getText().toString();
        loginUser(username, password);
        }


//the helper method called in the setOnclickListener method
private void loginUser(String username, String password) {
        //Log method for debugging purposes
        Log.i(TAG, "Attempting to login user " + username + "Password: " + password);
        //Start referencing the Parse and checking the credentials and move to the next activity if the credentials are correct
        //We using the method below for that
        ParseUser.logInInBackground(username, password, new LogInCallback() {
@Override
//Parse usually pass in the user and the exception. If the exception is null the login is suceeded and if not it is otherwise
public void done(ParseUser user, ParseException e) {
        //let us check if the parse exception is not null
        if(e != null){  //meaning the login has was unsuceessful
        Log.e(TAG, "Issues with the login", e);
        return;
        }
        // otherwise the login was succesful and now we have to navigate to the Homescreen Activity
        //we do so by creating our own method that will help with the navigation, goHomescreenActivity
        goHomescreenActivity();
        //Let's notify the user they have successfully logged in
        Log.i(TAG,"logging in ");
        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();


        }
        });
        }
        });
        }

private void goHomescreenActivity() {
        //we are using the intent system to navigate
        //we are naviagting from this context, to the main activity
        Intent i = new Intent(this,HomeScreenActivity.class);
        startActivity(i);
        //we do not want to keep asking the user to login anytime I exit the app without logging out!
        //so that is why we use the finish method
        finish();
        }
        }
