package com.example.getiondecreche;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

public class emploitdetemps extends AppCompatActivity {
    private boolean table_flg =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_emploitdetemps );
    }
    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */


    public void show(android.view.View view) {
        TableLayout table = findViewById(R.id.table);
        Button switchBtn = findViewById(R.id.switchBtn);

        table.setColumnCollapsed(1, table_flg);
        table.setColumnCollapsed(2, table_flg);

        if (table_flg){
            table_flg = false;
            switchBtn.setText("show detail");
        }
        else {
            table_flg = true;
            switchBtn.setText("hide detail");
        }
    }
}
