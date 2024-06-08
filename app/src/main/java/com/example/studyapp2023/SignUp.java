package com.example.studyapp2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studyapp2023.databinding.ActivitySignUpBinding;
import com.example.studyapp2023.databinding.ActivitySignUpPageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth fbu;

    String email;
    String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


         email=binding.email.getText().toString().trim();

         Pass=binding.password.getText().toString().trim();

         binding.LoginID.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SignUpWith4base(binding.email.getText().toString().trim(),binding.password.getText().toString().trim(),binding.NameId.getText().toString().trim());

             }
         });





    }

    private void SignUpWith4base(String email, String pass,String name) {
        fbu=FirebaseAuth.getInstance();

        binding.LoginID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.email.getText().toString().trim().length()==0 || binding.password.getText().toString().trim().length()==0){
                    Toast.makeText(SignUp.this, " التسجيل ", Toast.LENGTH_SHORT).show();

                    return;
                }
                fbu.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignUp.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                        
                        FillDataUser(email,name);

                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(SignUp.this, e.getMessage().toString().trim()+" لم تم التسجيل ", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });

    }

    private void FillDataUser(String email, String name) {
        FirebaseFirestore fbs=FirebaseFirestore.getInstance();
        String id =FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        Map<String,Object> Data=new HashMap<>();

        Data.put("ID",id);
        Data.put("Name",name);
        Data.put("email",email);

        fbs.collection("DataUsers").document(id).set(Data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });

    }


}