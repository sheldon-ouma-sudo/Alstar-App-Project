package com.example.alstarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;




public class SignUpActivity extends AppCompatActivity {
    //We initialize the variable to be able to identify the items in the activity xml
    private EditText etPhoneEmail;
    private EditText etPassword;
    private Button btnSighUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //connect/ the elements in the activity xml with the signup activity

        etPassword = findViewById(R.id.etPassword);
        etPhoneEmail= findViewById(R.id.etPhoneEmail);
        btnSighUp = findViewById(R.id.btnSignUp);

        //setOnclickListener on the signUp button so that when the user cicks on it he is taken to the Homescreen
        btnSighUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeScreenActivity.class);
                startActivity(intent);

            }

        });






    }
}