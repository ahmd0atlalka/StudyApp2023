// Updated MarksList.java
package com.example.studyapp2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studyapp2023.Adapter.MarkAdapter;
import com.example.studyapp2023.Domain.MarkDomain;
import com.example.studyapp2023.databinding.ActivityMarksListBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MarksList extends AppCompatActivity {

    private RecyclerView recyclerView;

    double sum=0;

    int c=0;
    private MarkAdapter markAdapter;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private ActivityMarksListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarksListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = binding.view;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainPage.class));
                finish();
            }
        });

        loadMarks();
    }

    private void loadMarks() {
        ArrayList<MarkDomain> markList = new ArrayList<>();
        markAdapter = new MarkAdapter(markList);
        recyclerView.setAdapter(markAdapter);

        firestore.collection("Data").document(auth.getUid()).collection("Marks")
                // Order the documents by the "mark" field in ascending order
                .orderBy("G", Query.Direction.DESCENDING)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String sub = document.getString("sub");
                            String des = document.getString("textMark");
                            int mark = document.getLong("G").intValue();
                            String date = document.getString("date");



                            if (sub == null) sub = "";
                            if (des == null) des = "";
                            if (date == null) date = "";

                            sum+=mark;

                            markList.add(new MarkDomain(sub,des,mark, date));
                        }
                        // Sort the markList alphabetically (assuming mark is a string)
                        binding.textAvg.setText(" المعدل هو : "+(int)(sum/markList.size())+" ");

                        markAdapter.notifyDataSetChanged();
                    }
                });
    }
}
