package com.example.finalproject_jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostKerjaActivity extends AppCompatActivity {

    private Button btnTambahPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_kerja);
        btnTambahPost=findViewById(R.id.tambahPostbtn);


        btnTambahPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), insertPostKerjaActivity.class));
            }
        });
    }
}