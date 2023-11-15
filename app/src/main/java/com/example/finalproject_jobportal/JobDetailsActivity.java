package com.example.finalproject_jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mDate;
    private TextView mDescription;
    private TextView mSkills;
    private TextView mSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        mTitle=findViewById(R.id.job_details_title);
        mDate=findViewById(R.id.job_details_date);
        mDescription=findViewById(R.id.job_details_description);
        mSkills=findViewById(R.id.job_details_skills);
        mSalary=findViewById(R.id.job_details_salary);

        //Menerima data dari all job activity menggunakan intent
        Intent intent=getIntent();

        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String description = intent.getStringExtra("description");
        String skills = intent.getStringExtra("skills");
        String salary = intent.getStringExtra("salary");

        mTitle.setText(title);
        mDate.setText(date);
        mDescription.setText(description);
        mSkills.setText(skills);
        mSalary.setText(salary);
    }
}