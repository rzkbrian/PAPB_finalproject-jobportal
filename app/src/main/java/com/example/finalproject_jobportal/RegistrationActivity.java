package com.example.finalproject_jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.common.reflection.qual.NewInstance;

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailRegistration;
    private EditText passRegistration;

    private Button btnSignup;
    private Button btnSingin;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);
        regiatration();
    }

    private void regiatration(){
        emailRegistration = findViewById(R.id.signupEmail);
        passRegistration = findViewById(R.id.signupPassword);

        btnSignup = findViewById(R.id.buttonSignupReg);
        btnSingin = findViewById(R.id.buttonSigninReg);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailRegistration.getText().toString().trim();
                String password = passRegistration.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailRegistration.setError("Email diperlukan");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    passRegistration.setError("Password tidak boleh kosong");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                            mDialog.dismiss();
                        } else{
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btnSingin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }

}