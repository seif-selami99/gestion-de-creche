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

public class affichagenws extends AppCompatActivity {

    ArrayList<News> newsList;
    ListView listView;
    AdapterAffichage adapter;
    News nws;
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */

    DatabaseReference reff;
    FirebaseAuth fAuth;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_afiichnws );

        newsList = new ArrayList<>();
        listView = findViewById(R.id.newsbase);
        listView.setFocusable(true);
        listView.setClickable(true);
        adapter = new AdapterAffichage(this, newsList);
        listView.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference("news");
        nws=new News();
    }


    public void affichetnws(View view) {
        reff.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                News nws = snapshot.getValue( com.example.getiondecreche.News.class );
                adapter.notifyDataSetChanged();
                newsList.add(nws);
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
