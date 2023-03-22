package com.example.getiondecreche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ParentAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_parent );
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); //deconecti

        startActivity( new Intent( getApplicationContext(), firstActivity.class ) );

    }

    public void emploitdetemp(View v) {
        Intent intent = new Intent( this, emploitdetemps.class );
        startActivity( intent );


    }

    public void aff(View view) {
        Intent intent = new Intent( this,affichagenws.class );
        startActivity( intent );


    }


    public void listteachet(View view) {
        Intent intent =new Intent(getApplicationContext(),aficheprof.class);
        startActivity( intent );
    }
}
