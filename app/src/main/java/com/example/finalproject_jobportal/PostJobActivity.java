package com.example.finalproject_jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproject_jobportal.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJobActivity extends AppCompatActivity {

    //Recycler View
    private RecyclerView recyclerView;

    private TextView btnBackHome;
    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference postKerjaDatabase;
    private Button fabBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        fabBtn=findViewById(R.id.fab_add);
        btnBackHome=findViewById(R.id.btnBackToHome);
        backToHome();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        postKerjaDatabase = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        recyclerView=findViewById(R.id.recycler_job_post_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InsertJobPostActivity.class));
            }
        });
    }

    private void backToHome(){
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(postKerjaDatabase, Data.class)
                        .build();
        FirebaseRecyclerAdapter<Data,myViewHolder>adapter= new FirebaseRecyclerAdapter<Data, myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder viewHolder, int position, @NonNull Data model) {
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobData(model.getDate());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setJobSkill(model.getSkills());
                viewHolder.setJobSalary(model.getSalary());
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };

        recyclerView.setAdapter(adapter);

    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public myViewHolder(View itemView){
            super(itemView);
            myview=itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle =myview.findViewById(R.id.job_title);
            mTitle.setText(title);
        }

        public void setJobData(String date){
            TextView mDate = myview.findViewById(R.id.job_date);
            mDate.setText(date);
        }

        public void setJobDescription(String description){
            TextView mDeskripsi = myview.findViewById(R.id.job_description);
            mDeskripsi.setText(description);
        }

        public void setJobSkill(String skill){
            TextView mSkill = myview.findViewById(R.id.job_skills);
            mSkill.setText(skill);
        }

        public void setJobSalary(String gaji){
            TextView mGaji = myview.findViewById(R.id.job_salary);
            mGaji.setText(gaji);
        }

    }
}