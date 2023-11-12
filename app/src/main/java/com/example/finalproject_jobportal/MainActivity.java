package com.example.finalproject_jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private Button btnSignin;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginFunction();
    }

    private void loginFunction(){
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        btnSignin = findViewById(R.id.buttonSignin);
        btnSignup = findViewById(R.id.buttonSignup);



        btnSignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });
    }
}