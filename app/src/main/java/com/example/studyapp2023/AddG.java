package com.example.studyapp2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studyapp2023.databinding.ActivityAddGBinding;
import com.example.studyapp2023.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddG extends AppCompatActivity {
    FirebaseAuth fbu;
    FirebaseFirestore fbs;
    ActivityAddGBinding binding;
    Map<String,Object> dataof;

    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_g);
        binding=ActivityAddGBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fbu= FirebaseAuth.getInstance();
        fbs= FirebaseFirestore.getInstance();






        binding.LoginID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.Sub.getText().toString().trim().isEmpty()) {
                    binding.Sub.setError("ادخل موضوع");
                    return;
                }

                if (binding.SubDes.getText().toString().trim().isEmpty()) {
                    binding.SubDes.setError("العلامة يجب ان تكون  من 0 الى 100");
                    return;
                }

                String sub=binding.Sub.getText().toString().trim();

                String G=binding.SubDes.getText().toString();

                int g=Integer.parseInt(G);
                if (g < 0 || g > 100) {
                    binding.SubDes.setError("العلامة يجب ان تكون  من 0 الى 100");
                    return;
                }




                if(g<=55){
                    s="غير كاف";
                } else if (g<70 && g>55) {
                    s=" كاف";

                } else if (g>70 && g<85) {
                    s="جيد";
                }
                else{
                    s="ممتاز";

                }


                String id= UUID.randomUUID().toString();
                dataof=new HashMap<>();
                dataof.put("G",g);
                dataof.put("sub",sub);
                dataof.put("id",id);
                dataof.put("textMark",s);
                dataof.put("Data", FieldValue.serverTimestamp());

                fbs.collection("Data").document(fbu.getUid().toString()).collection("Marks").document(id).set(dataof).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity( new Intent(getApplicationContext(), MainPage.class));
                        finish();
                    }
                });
            }
        });
    }
}