package com.example.getiondecreche;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
    private ArrayList<News> arrayadapter;
    Context context;
    public NewsAdapter(Context context, ArrayList<News> listNews) {
        this.context = context;
        this.arrayadapter = listNews;
    }

    @Override
    public int getCount() {
        return arrayadapter.size();
    }

    @Override
    public News getItem(int position) {
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
            convertView = inflater.inflate( R.layout.item_news, null );
        }
        TextView news = convertView.findViewById( R.id.nwstxt );
        news.setText( getItem( position ).getNws() );////







        return convertView;
    }

}
