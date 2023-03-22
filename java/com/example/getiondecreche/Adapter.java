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
/* copy right sellamni seyf eddin
* torech adam
* belaifa mohamed*/
public class Adapter extends BaseAdapter {
    private ArrayList<elev> arrayadapter;
    Context context;

    /* copy right sellami seyfeddin
     * torch adam
     * belaifa mohamed */


    String mail;


    public Adapter(Context context, ArrayList<elev> contactlist) {
        this.context = context;
        this.arrayadapter = contactlist;
    }

    @Override
    public int getCount() {
        return arrayadapter.size();
    }

    @Override
    public elev getItem(int position) {
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
            convertView = inflater.inflate( R.layout.item_enfant, null );
        }
        TextView name = convertView.findViewById( R.id.nameTxt );
        name.setText( getItem( position ).getName() );////
        final TextView tel = convertView.findViewById( R.id.telNumTxt );
        tel.setText( getItem( position ).getTelNum() );/////


        ImageButton btnDell = convertView.findViewById( R.id.delbtn );
        btnDell.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                deleteContact(position);
            }
        } );

        ImageButton btnsms = convertView.findViewById( R.id.smsBtn );
        btnsms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText sendsms = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Send Sms");
                passwordResetDialog.setMessage("Enter un sms.");
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

    private void deleteContact(int position/*, DataSnapshot snapshot*/) {
        //final elev elev = snapshot.getValue( com.example.getiondecreche.elev.class );
        String ideleve = getItem( position ).getUserId();
        arrayadapter.remove( position );
        //arrayadapter.remove( elev );
        //snapshot.getKey();
        notifyDataSetChanged();
        ((ListEleve)context).removeElement(ideleve);

        //////del from fire base
    }

    private void sendSMS(String telNum) {
        SmsManager smsManager = SmsManager.getDefault();
        ///alert dialogue
        smsManager.sendTextMessage( String.valueOf( telNum ), null, mail,
                null, null );
    }

}


