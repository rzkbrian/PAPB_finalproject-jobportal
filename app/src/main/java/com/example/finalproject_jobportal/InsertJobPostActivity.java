package com.example.finalproject_jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject_jobportal.model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.util.Date;

public class InsertJobPostActivity extends AppCompatActivity {
    private EditText job_title;
    private EditText job_description;
    private EditText job_skills;
    private EditText job_Salary;

    private Button btn_post_job;
    private ImageView backbtn;

    //Firebase..
    private FirebaseAuth mAuth;
    private DatabaseReference mJobPost;

    private DatabaseReference mPublicDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        String uId = mUser.getUid();
        backbtn=findViewById(R.id.backbtn);
        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        mPublicDatabase=FirebaseDatabase.getInstance().getReference().child("Public database");

        InsertJob();
        backjobpost();
    }

    private void backjobpost(){
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PostJobActivity.class));
            }
        });
    }
    private void InsertJob(){
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_skills = findViewById(R.id.job_skills);
        job_Salary = findViewById(R.id.job_salary);
        btn_post_job = findViewById(R.id.btn_job_post);

        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = job_title.getText().toString().trim();
                String description = job_description.getText().toString().trim();
                String skills = job_skills.getText().toString().trim();
                String salary = job_Salary.getText().toString().trim();

                if(TextUtils.isEmpty(title)){
                    job_title.setError("Judul Pekerjaan Diperlukan");
                    return;
                }
                if(TextUtils.isEmpty(description)){
                    job_description.setError("Deskripsi Pekerjaan Diperlukan");
                    return;
                }
                if(TextUtils.isEmpty(skills)){
                    job_skills.setError("Skill Pekerjaan Diperlukan");
                    return;
                }
                if(TextUtils.isEmpty(salary)){
                    job_Salary.setError("Gaji Pekerja an Diperlukan");
                    return;
                }

                String id = mJobPost.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());

                Data data = new Data(title, description, salary, skills, id, date);
                mJobPost.child(id).setValue(data);

                mPublicDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), InsertJobPostActivity.class));

            }
        });
    }
}