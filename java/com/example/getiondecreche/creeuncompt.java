package com.example.getiondecreche;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class creeuncompt extends AppCompatActivity {
    EditText eusername,eemail,ephone,epassword;
    Button btnenrigeste;
    FirebaseAuth base;
    ProgressBar progressBar;



/* copy right sellami seyfeddin
* torch adam
* belaifa mohamed */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creeuncompt);



        eusername= findViewById(R.id.edituser);
        eemail= findViewById(R.id.editemail);
        ephone= findViewById(R.id.editphone);
        epassword= findViewById(R.id.editpassword);
        btnenrigeste=findViewById(R.id.btn1);

        base= FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if (base.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),loginActivity.class));
            finish();
        }



        btnenrigeste.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                String email=eemail.getText().toString().trim();
                String pass=epassword.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    eemail.setError("Email is Require");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    epassword.setError("Password is Require");
                    return;
                }
                if (pass.length()<6){
                    epassword.setError("password must > 6 characters");
                    return;


                }
                progressBar.setTransitionVisibility(View.VISIBLE);


                //enregisrer user dans le firebase
                base.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(creeuncompt.this, "user cree", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),loginActivity.class));


                        }else {
                            Toast.makeText(creeuncompt.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }



                    }
                });



            }
        });






    }
}