package com.example.getiondecreche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin );
        Button btn=findViewById( R.id.listBtn );
       btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent activity = new Intent( getApplicationContext(),ListEleve.class );
               startActivity( activity );

                /* copy right sellami seyfeddin
                 * torch adam
                 * belaifa mohamed */

            }
       } );
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); //deconecti
        startActivity(new Intent(getApplicationContext(),firstActivity.class));
        finish();
    }

    public  void showListEleve(View v){
        Intent intent = new Intent( this,ListEleve.class );
        startActivity( intent );


    }
    public void emploitdetemps (View v ){
        Intent intent=new Intent(this,emploitdetemps.class);
        startActivity( intent );


    }
    public void News (View v){
        Intent intent= new Intent(this,listNews.class);
        startActivity( intent );


    }


    public void listteachet(View view) {
        Intent intent= new Intent(getApplicationContext(),Listprof.class);
        startActivity( intent );

    }
}