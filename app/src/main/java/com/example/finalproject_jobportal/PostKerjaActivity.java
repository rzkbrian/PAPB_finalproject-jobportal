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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostKerjaActivity extends AppCompatActivity {

    //Recycler View
    private RecyclerView recyclerView;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference postKerjaDatabase;
    private Button btnTambahPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_kerja);
        btnTambahPost=findViewById(R.id.tambahPostbtn);

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


        btnTambahPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), insertPostKerjaActivity.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Data,myViewHolder>adapter= new FirebaseRecyclerAdapter<Data, myViewHolder>(
                Data.class,
                R.layout.jobpost_items,
                myViewHolder.class,
                postKerjaDatabase;
        ) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder viewHolder, int position, @NonNull Data model) {


            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        }

    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public myViewHolder(View itemView){
            super(itemView);
            myview=itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle =myview.findViewById(R.id.judul_pekerjaan);
            mTitle.setText(title);
        }

        public void setJobData(String date){
            TextView mDate = myview.findViewById(R.id.tanggal_pekerjaan);
            mDate.setText(date);
        }

        public void setJobDescription(String description){
            TextView mDeskripsi = myview.findViewById(R.id.deskripsi_pekerjaan);
            mDeskripsi.setText(description);
        }

        public void setJobSkill(String skill){
            TextView mSkill = myview.findViewById(R.id.skill_pekerjaan);
            mSkill.setText(skill);
        }

        public void setJobSalary(String gaji){
            TextView mGaji = myview.findViewById(R.id.gaji_pekerjaan);
            mGaji.setText(gaji);
        }

    }
}