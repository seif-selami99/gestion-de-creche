package com.example.getiondecreche;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/* copy right sellami seyfeddin
* torch adam
* belaifa mohamed */

public class loginActivity extends AppCompatActivity {
    EditText epassword,eemail;
    Button btnlogin;
    TextView creecmpt ,ForgetPassorld;
    ProgressBar progressBar;
    FirebaseAuth base;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginactivity);
        epassword=findViewById(R.id.editTextTextPassword);
        eemail=findViewById(R.id.editTextTextEmailAddress2);
        btnlogin=findViewById(R.id.button);
        base=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar2);
        creecmpt=findViewById(R.id.textView);
        ForgetPassorld=findViewById(R.id.textViewForgetpassorld);





        btnlogin.setOnClickListener(new View.OnClickListener() {
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
                //authentifier l'utilisateur
                base.signInWithEmailAndPassword(email,pass);


                //authentifier d'utilisateur
                base.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "logged succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),admin.class));
                        }else {
                            Toast.makeText(loginActivity.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }




                    }
                });





            }
        });








        creecmpt=findViewById(R.id.textView);
        creecmpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),creeuncompt.class));

            }
        });



        ForgetPassorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        base.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(loginActivity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(loginActivity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }

    public void creecmt(View view) {
        Intent intent= new Intent(getApplicationContext(),creeuncompt.class);
        startActivity( intent );
        finish();
    }
}