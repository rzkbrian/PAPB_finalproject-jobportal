package com.example.finalproject_jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class insertPostKerjaActivity extends AppCompatActivity {
    private EditText judulPekerjaan;
    private EditText deskripsiPekerjaan;
    private EditText skillPekerjaan;
    private EditText gajiPekerjaan;

    private Button submitPostPekerjaan;

    //Firebase..
    private FirebaseAuth mAuth;
    private DatabaseReference MJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post_kerja);

        masukkanKerja();
    }

    private void masukkanKerja(){
        judulPekerjaan = findViewById(R.id.inputJobTitle);
        deskripsiPekerjaan = findViewById(R.id.inputJobDesc);
        skillPekerjaan = findViewById(R.id.inputJobSkill);
        gajiPekerjaan = findViewById(R.id.inputJobSalary);
        submitPostPekerjaan = findViewById(R.id.selesaiPostBtn);

        submitPostPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul = judulPekerjaan.getText().toString().trim();
                String deskripsi = deskripsiPekerjaan.getText().toString().trim();
                String skill = skillPekerjaan.getText().toString().trim();
                String gaji = gajiPekerjaan.getText().toString().trim();

                if(TextUtils.isEmpty(judul)){
                    judulPekerjaan.setError("Judul Pekerjaan Diperlukan");
                    return;
                }
                if(TextUtils.isEmpty(deskripsi)){
                    deskripsiPekerjaan.setError("Deskripsi Pekerjaan Diperlukan");
                    return;
                }
                if(TextUtils.isEmpty(skill)){
                    skillPekerjaan.setError("Skill Pekerjaan Diperlukan");
                    return;
                }
                if(TextUtils.isEmpty(gaji)){
                    gajiPekerjaan.setError("Gaji Pekerjaan Diperlukan");
                }
            }
        });
    }
}