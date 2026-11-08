package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SticlaAdapter extends BaseAdapter {

    private List<Sticla> sticle=null;
    private Context ctx;
    private int resursaLayout;

    public SticlaAdapter(List<Sticla> sticle, Context ctx, int resursaLayout) {
        this.sticle = sticle;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return sticle.size();
    }

    @Override
    public Object getItem(int position) {
        return sticle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,viewGroup,false);

        TextView Brand=v.findViewById(R.id.Brand);
        TextView Tip=v.findViewById(R.id.Tip);
        TextView Volum=v.findViewById(R.id.Volum);
        TextView Pret=v.findViewById(R.id.Pret);
        TextView Magazine=v.findViewById(R.id.Magazine);
        TextView Nota=v.findViewById(R.id.Nota);

        Sticla sticla=(Sticla) getItem(position);

        String b=sticla.brand+"\t";
        Brand.setText(b);
        String tipul=sticla.tip_apa.toString()+"\t";
        Tip.setText(tipul);
        String vol=sticla.volum+"ml\t";
        Volum.setText(vol);
        String p=sticla.pret+" RON";
        Pret.setText(p);
        String mag="";
        for(int j=0;j<sticla.magazine.toArray().length;j++)
        {
            mag=mag+sticla.magazine.toArray()[j];
            if(j!=sticla.magazine.toArray().length-1)
                mag=mag+", ";
        }
        Magazine.setText(mag);
        String n= String.valueOf(sticla.nota);
        Nota.setText(n);

        return v;
    }
}
