package com.example.studyapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studyapp2023.R;
import com.example.studyapp2023.databinding.ActivityMainPageBinding;
import com.example.studyapp2023.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

public class MainPage extends AppCompatActivity {
    ActivityMainPageBinding Binding;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Binding=ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.TOaddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpPage.class));
                finish();
            }
        });

        Binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddG.class));
                finish();
            }
        });

        Binding.AllDataSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoursesListActivity.class));
                finish();
            }
        });


        Binding.Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Splash.class));
                finish();
            }
        });

        Binding.gradeID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MarksList.class));
                finish();
            }
        });

        Binding.ListCoID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoursesListActivity.class));
                finish();
            }
        });


        Binding.textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoursesListActivity.class));
                finish();
            }
        });

        Binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MarksList.class));
                finish();
            }
        });


        FirebaseAuth FBU=FirebaseAuth.getInstance();
        FirebaseFirestore fbs=FirebaseFirestore.getInstance();


        //اضافة اخر موضوع
        fbs.collection("Data").document(FBU.getUid()).collection("Courses")
                .orderBy("Data", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot d : queryDocumentSnapshots) {
                            String description = d.getString("des");
                            String sub = d.getString("sub");
                            Date timestamp = d.getDate("timestamp");

                            Binding.textView8.setText(description);
                            Binding.textView7.setText(sub);





                        }
                    }
                });


        LastSub();









    }

    private void LastSub() {


    }
}