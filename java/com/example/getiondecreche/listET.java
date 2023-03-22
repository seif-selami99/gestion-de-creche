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

public class listET extends AppCompatActivity {
    ArrayList<elev> enfantlistList;  // datasource
    ListView listView;
    AdapterET adapter;
    elev elv;
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */

    DatabaseReference reff;
    FirebaseAuth fAuth;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_e_t );
        enfantlistList = new ArrayList<>();
        listView = findViewById(R.id.listenfant);
        listView.setFocusable(true);
        listView.setClickable(true);
        adapter = new AdapterET(this, enfantlistList);
        listView.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference("elev");
        elv=new elev();





    }

    public void affichet(View view) {
        reff.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                elev elev = snapshot.getValue( com.example.getiondecreche.elev.class );
                adapter.notifyDataSetChanged();
                enfantlistList.add(elev);
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