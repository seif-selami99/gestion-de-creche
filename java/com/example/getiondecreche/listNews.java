package com.example.getiondecreche;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class listNews extends AppCompatActivity {

    ArrayList<News> newslistList;  // datasource
    ListView listView;
    EditText newsEdit;
    NewsAdapter adapter;
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */

     News nws ;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.list_news );

        newslistList = new ArrayList<>();
        listView = findViewById(R.id.Newslist);
        listView.setFocusable(true);
        listView.setClickable(true);
        newsEdit = findViewById(R.id.newsEdit);
        registerForContextMenu(newsEdit);

        adapter = new NewsAdapter(this, newslistList);
        listView.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference("news");
        nws=new News();
    }

    public void addnews(View view) {

        String n = newsEdit.getText().toString().trim();


        if (TextUtils.isEmpty( n )) {
            newsEdit.setError( "Name is Required" );
            return;}


            nws.setNws(newsEdit.getText().toString().trim());

            nws.setUserId( UserID );
            reff.push().setValue( nws );
            Toast.makeText( listNews.this, "the child  " + n + " is Added To Your List", Toast.LENGTH_LONG ).show();
            newsEdit.getText().clear();

            reff.addChildEventListener( new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    News news = snapshot.getValue( com.example.getiondecreche.News.class );
                    adapter.notifyDataSetChanged();
                    newslistList.add( news );

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull final DataSnapshot snapshot) {
                    final elev elev = snapshot.getValue( com.example.getiondecreche.elev.class );
                    newslistList.remove( elev );
                    newslistList.remove( snapshot.getKey() );


                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            } );
            adapter.notifyDataSetChanged();

        }
    }

