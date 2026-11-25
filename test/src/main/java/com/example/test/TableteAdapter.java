package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TableteAdapter extends BaseAdapter {

    List<Tableta> tablete;
    Context ctx;
    int resursaLayout;

    public TableteAdapter(List<Tableta> tablete, Context ctx, int resutsaLayout) {
        this.tablete = tablete;
        this.ctx = ctx;
        this.resursaLayout = resutsaLayout;
    }

    @Override
    public int getCount() {
        return tablete.size();
    }

    @Override
    public Object getItem(int position) {
        return tablete.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(ctx);
        View v=layoutInflater.inflate(resursaLayout,viewGroup,false);

        TextView txtId=v.findViewById(R.id.ID_textView);
        TextView txtModel=v.findViewById(R.id.Model_textView);
        TextView txtSpatiu=v.findViewById(R.id.Spatiu_textView);
        TextView txtSistemOp=v.findViewById(R.id.SistemOp_textView);

        Tableta tableta=tablete.get(position);

        String ID_ul="ID: "+tableta.idTableta;
        txtId.setText(ID_ul);
        String mod="Model: "+tableta.model;
        txtModel.setText(mod);
        String spa="Spatiu: "+tableta.spatiuMemoriel+" GB";
        txtSpatiu.setText(spa);
        String sis="Sistem de operare: "+tableta.sistem_de_operare.toString();
        txtSistemOp.setText(sis);

        return v;
    }
}
