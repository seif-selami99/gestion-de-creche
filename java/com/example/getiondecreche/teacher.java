package com.example.getiondecreche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class teacher extends AppCompatActivity {
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher );
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); //deconecti
        startActivity(new Intent(getApplicationContext(),firstActivity.class));

    }

    public void emploi(View view) {
        startActivity(new Intent(getApplicationContext(),emploitdetemps.class));


    }

    public void ET(View view) {
        startActivity(new Intent(getApplicationContext(),listET.class) );


    }

    public void afiichnws(View view) {
        startActivity( new Intent(getApplicationContext(),affichagenws.class) );


    }


}