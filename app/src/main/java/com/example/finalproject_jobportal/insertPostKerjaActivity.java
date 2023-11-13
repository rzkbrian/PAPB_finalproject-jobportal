package com.example.finalproject_jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject_jobportal.model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.util.Date;

public class insertPostKerjaActivity extends AppCompatActivity {
    private EditText judulPekerjaan;
    private EditText deskripsiPekerjaan;
    private EditText skillPekerjaan;
    private EditText gajiPekerjaan;

    private Button submitPostPekerjaan;

    //Firebase..
    private FirebaseAuth mAuth;
    private DatabaseReference mJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post_kerja);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        String uId = mUser.getUid();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

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
                    return;
                }

                String id = mJobPost.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());

                Data data = new Data(judul, deskripsi, gaji, skill, id, date);
                mJobPost.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),insertPostKerjaActivity.class));

            }
        });
    }
}