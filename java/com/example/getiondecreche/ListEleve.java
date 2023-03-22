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
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListEleve extends AppCompatActivity {

    ArrayList<elev> enfantlistList;  // datasource
    ListView listView;
    EditText nameEdit, telEdit;
    Adapter adapter;

/* copy right sellami seyfeddin
* torch adam
* belaifa mohamed */


    elev elv;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    String UserID;
    RecyclerView recyclerView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_desenfant);

        enfantlistList = new ArrayList<>();
        listView = findViewById(R.id.listenfant);
        listView.setFocusable(true);
        listView.setClickable(true);
        nameEdit = findViewById(R.id.nameEdit);
        registerForContextMenu(nameEdit);
        telEdit = findViewById(R.id.teledit);
        adapter = new Adapter(this, enfantlistList);
        listView.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference("elev");
        elv=new elev();


    }

    /*@Override
    protected void onResume() {
        super.onResume();
        registerForContextMenu(listView);

    }*/

  /*  public void makeCall(String tel) {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }*/


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




        elv.setName(nameEdit.getText().toString().trim());
        elv.setTelNum(t);
        elv.setUserId(UserID);
        reff.push().setValue(elv);
        Toast.makeText(ListEleve.this, "the child  "+n+" is Added To Your List", Toast.LENGTH_LONG).show();
        nameEdit.getText().clear();
        telEdit.getText().clear();
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
            public void onChildRemoved(@NonNull final DataSnapshot snapshot) {
//                final elev elev = snapshot.getValue(com.example.getiondecreche.elev.class);
//                enfantlistList.remove( elev );
//                enfantlistList.remove( snapshot.getKey() );
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


    public void removeElement(final String ideleve) {
        Query query = reff.orderByChild("userId").equalTo( ideleve );
        query.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()
                     ) {
                    ds.getRef().removeValue();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
}
