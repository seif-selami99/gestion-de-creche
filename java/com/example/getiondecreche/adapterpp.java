package com.example.getiondecreche;

import android.content.Context;
import android.content.DialogInterface;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class adapterpp extends BaseAdapter {

    private ArrayList<prof> arrayadapter;
    Context context;
    String mail;

    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */


    public adapterpp(Context context, ArrayList<prof> contactlist) {
        this.context = context;
        this.arrayadapter = contactlist;
    }

    @Override
    public int getCount() {
        return arrayadapter.size();
    }

    @Override
    public prof getItem(int position) {
        return arrayadapter.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from( context );
            convertView = inflater.inflate( R.layout.pp, null );
        }
        TextView name = convertView.findViewById( R.id.nameTxt );
        name.setText( getItem( position ).getName() );////
        final TextView tel = convertView.findViewById( R.id.telNumTxt );
        tel.setText( getItem( position ).getTelNum() );/////




        ImageButton btnsms = convertView.findViewById( R.id.smsBtn );
        btnsms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText sendsms = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Send Sms");
                passwordResetDialog.setMessage("entrer un remarque !");
                passwordResetDialog.setView(sendsms);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        mail = sendsms.getText().toString();




                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();
                sendSMS( getItem( position ).getTelNum() );
            }
        } );

        return convertView;
    }



    private void sendSMS(String telNum) {
        SmsManager smsManager = SmsManager.getDefault();
        ///alert dialogue
        smsManager.sendTextMessage( String.valueOf( telNum ), null, mail,
                null, null );
    }
}
