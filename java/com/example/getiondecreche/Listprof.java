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

public class Listprof extends AppCompatActivity {
    ArrayList<prof> proflist;  // datasource
    ListView listView;
    EditText nameEdit, telEdit;
    profAdapter adapter;
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */


    prof prof;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_listprof );
        proflist = new ArrayList<>();
        listView = findViewById( R.id.listenfant );
        listView.setFocusable( true );
        listView.setClickable( true );
        nameEdit = findViewById( R.id.nameEdit );
        registerForContextMenu( nameEdit );
        telEdit = findViewById( R.id.teledit );
        adapter = new profAdapter( this, proflist );
        listView.setAdapter( adapter );

        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference( "prof" );
        prof = new prof();

    }

    public void addContact(View view) {
        String n = nameEdit.getText().toString().trim();
        String t = telEdit.getText().toString().trim();
        /*enfantlistList.add(new elev(n,t));
        adapter.notifyDataSetChanged();*/

        if (TextUtils.isEmpty(n)){
            nameEdit.setError("Name is Required");
            return;
        }
        if (TextUtils.isEmpty(t)){
            telEdit.setError("Number phone  is Required");
            return;
        }


        prof.setName( nameEdit.getText().toString().trim() );
        prof.setTelNum( t );
        prof.setUserId( UserID );
        reff.push().setValue( prof );
        Toast.makeText( Listprof.this, "the teacher  " + n + " is Added To Your List", Toast.LENGTH_LONG ).show();
        nameEdit.getText().clear();
        telEdit.getText().clear();

        reff.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                prof prf = snapshot.getValue( com.example.getiondecreche.prof.class );
                adapter.notifyDataSetChanged();
                proflist.add(prf);
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
        adapter.notifyDataSetChanged();
    }
}