package com.example.studyapp2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studyapp2023.Adapter.CoursesAdapter;
import com.example.studyapp2023.Domain.CoursesDomain;
import com.example.studyapp2023.databinding.ActivityCoursesListBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class CoursesListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCourseList;
    private RecyclerView recyclerViewCourse;
    FirebaseFirestore fbs;
    FirebaseAuth fbu;

    String date;
    ActivityCoursesListBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        bi = ActivityCoursesListBinding.inflate(getLayoutInflater());
        setContentView(bi.getRoot());

        recyclerViewCourse = findViewById(R.id.view);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        bi.imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainPage.class));
                finish();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<CoursesDomain> items = new ArrayList<>();
        fbu = FirebaseAuth.getInstance();
        fbs = FirebaseFirestore.getInstance();

        adapterCourseList = new CoursesAdapter(items);
        recyclerViewCourse.setAdapter(adapterCourseList);

        fbs.collection("Data").document(fbu.getUid()).collection("Courses")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot d : queryDocumentSnapshots) {
                            String sub = d.getString("sub");
                            String des = d.getString("des");
                            String picPath = d.getString("picPath");
                             date = d.getString("date");

                            if (sub == null) sub = "";
                            if (des == null) des = "";
                            if (picPath == null) picPath = "";
                            if (date == null){ date = "";
                                Toast.makeText(getApplicationContext(),"Ddd",Toast.LENGTH_SHORT).show();

                            }



                            items.add(new CoursesDomain(sub, des, picPath, date));

                        }
                        adapterCourseList.notifyDataSetChanged();
                    }
                });
    }
}
