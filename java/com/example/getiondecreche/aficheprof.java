package com.example.getiondecreche;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class aficheprof extends AppCompatActivity {
    ArrayList<prof> profList;  // datasource
    ListView listView;
    adapterpp adapter;
    prof prf;
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */

    DatabaseReference reff;
    FirebaseAuth fAuth;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.aficheprof );
        profList = new ArrayList<>();
        listView = findViewById(R.id.listenfant);
        listView.setFocusable(true);
        listView.setClickable(true);
        adapter = new adapterpp(this, profList);
        listView.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference("elev");
        prf=new prof();


    }

    public void affprof(View view) {

        reff.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                prof prof = snapshot.getValue( com.example.getiondecreche.prof.class );
                adapter.notifyDataSetChanged();
                profList.add(prof);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
}