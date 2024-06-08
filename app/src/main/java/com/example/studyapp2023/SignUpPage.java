package com.example.studyapp2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.studyapp2023.databinding.ActivitySignUpPageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SignUpPage extends AppCompatActivity {

    ActivitySignUpPageBinding binding;
    FirebaseAuth fbu;
    FirebaseFirestore fbs;
    TextInputEditText editText1, editText2, dateEditText;
    Map<String, Object> dataof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        binding = ActivitySignUpPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fbu = FirebaseAuth.getInstance();
        fbs = FirebaseFirestore.getInstance();

        // Initialize the dateEditText
        dateEditText = findViewById(R.id.Date);

        binding.LoginID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sub = binding.Sub.getText().toString().trim();
                String des = binding.SubDes.getText().toString();
                String date =binding.Date.getText().toString();
                String id = UUID.randomUUID().toString();

                if(binding.Sub.getText().toString().isEmpty()){
                    binding.Sub.setError("يرجى ادخال بيانات الموضوع");
                    return;
                }
                if(binding.SubDes.getText().toString().isEmpty()){
                    binding.SubDes.setError("يرجى ادخال بيانات الموضوع");
                    return;
                }
                if(date.isEmpty()){
                    binding.Date.setError("يرجى ادخال بيانات الموضوع");
                    return;
                }



                dataof = new HashMap<>();
                dataof.put("des", des);
                dataof.put("sub", sub);
                dataof.put("id", id);
                dataof.put("date", date);
                dataof.put("Data", FieldValue.serverTimestamp());


                fbs.collection("Data").document(fbu.getUid()).collection("Courses").document(id).set(dataof).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpPage.this, "Failed to add data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
