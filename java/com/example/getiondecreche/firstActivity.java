package com.example.getiondecreche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class firstActivity extends AppCompatActivity {
    Button btnadmin,btnparent,btnteacher;
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_first );
        btnadmin=findViewById( R.id.adminbtn );
        btnparent=findViewById( R.id.parentbtn );
        btnteacher=findViewById( R.id.teacherbtn );


        btnadmin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity=new Intent( getApplicationContext(),loginActivity.class );
                startActivity( activity );



            }
        } );

        btnparent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity=new Intent( getApplicationContext(),loginparent.class );
                startActivity( activity );



            }
        } );
        btnteacher.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity=new Intent( getApplicationContext(),loginTeacher.class );
                startActivity( activity );



            }
        } );
    }
}