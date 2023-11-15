package com.example.finalproject_jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;


import com.example.finalproject_jobportal.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllJobActivity extends AppCompatActivity {

    private TextView backHomeBtn;

    //Recycler...
    private RecyclerView recyclerView;

    //Firebae
    private DatabaseReference mAllJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job);
        backHomeBtn = findViewById(R.id.btnBackHome);
        //Database
        mAllJobPost = FirebaseDatabase.getInstance().getReference().child("Public database");
        mAllJobPost.keepSynced(true);


        backHomeBtn();
        recyclerView = findViewById(R.id.recycler_all_job);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void backHomeBtn() {
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mAllJobPost, Data.class)
                        .build();

        FirebaseRecyclerAdapter<Data, AllJobPostViewHolder> adapter =
                new FirebaseRecyclerAdapter<Data, AllJobPostViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AllJobPostViewHolder viewHolder, int position, @NonNull Data model) {
                        viewHolder.setJobTitle(model.getTitle());
                        viewHolder.setJobDate(model.getDate());
                        viewHolder.setJobDesc(model.getDescription());
                        viewHolder.setJobSkill(model.getSkills());
                        viewHolder.setJobSalary(model.getSalary());

                        viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(),JobDetailsActivity.class);

                                intent.putExtra("title",model.getTitle());
                                intent.putExtra("date",model.getDate());
                                intent.putExtra("description",model.getDescription());
                                intent.putExtra("skills",model.getSkills());
                                intent.putExtra("salary",model.getSalary());

                                startActivity(intent);

                            }
                        });
                    }



                    @NonNull
                    @Override
                    public AllJobPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alljobpost, parent, false);
                        return new AllJobPostViewHolder(view);
                    }
                };

        recyclerView.setAdapter(adapter);

    }


    public static class AllJobPostViewHolder extends RecyclerView.ViewHolder {

        View myview;

        public AllJobPostViewHolder(View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setJobTitle(String title) {
            TextView mtitle = myview.findViewById(R.id.all_job_post_title);
            mtitle.setText(title);
        }

        public void setJobDate(String date) {
            TextView mDate = myview.findViewById(R.id.all_job_post_date);
            mDate.setText(date);
        }

        public void setJobDesc(String desc) {
            TextView mDesc = myview.findViewById(R.id.all_job_post_description);
            mDesc.setText(desc);
        }

        public void setJobSkill(String skill) {
            TextView mSkill = myview.findViewById(R.id.all_job_post_skills);
            mSkill.setText(skill);
        }

        public void setJobSalary(String salary) {
            TextView mSalary = myview.findViewById(R.id.all_job_post_salary);
            mSalary.setText(salary);
        }


    }
}



