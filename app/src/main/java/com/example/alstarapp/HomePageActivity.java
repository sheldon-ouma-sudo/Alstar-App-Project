    package com.example.alstarapp;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

    import com.parse.ParseUser;

    public class HomePageActivity extends AppCompatActivity {
        private Button btnSIGNUP;
        private Button btnLOGIN;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);
            if(ParseUser.getCurrentUser() != null){
                gotoLoginActivity();
            }

    //referencing the activity items
          btnLOGIN=findViewById(R.id.btnLOGIN);
          btnSIGNUP= findViewById(R.id.btSIGNUP);

          btnSIGNUP.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  goSignUpAcitivity();
              }
          });

          btnLOGIN.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                gotoLoginActivity();
              }
          });

        }
    //navigation to the sigup activity
        private void goSignUpAcitivity() {
            Intent intent = new Intent(this,SignUpActivity.class);
            startActivity(intent);

        }
        //navigation to the login activity
        private void gotoLoginActivity() {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }