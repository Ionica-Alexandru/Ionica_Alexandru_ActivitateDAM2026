package com.example.pregatire_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SpitalAdapter extends BaseAdapter {

    List<Spital> spitale;
    Context ctx;
    int resursaLayout;

    public SpitalAdapter(List<Spital> spitale, Context ctx, int resursaLayout) {
        this.spitale = spitale;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return spitale.size();
    }

    @Override
    public Object getItem(int position) {
        return spitale.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(ctx);
        View v=layoutInflater.inflate(resursaLayout,viewGroup,false);

        TextView nume=v.findViewById(R.id.Nume_Spital);
        TextView tipulSpitalului=v.findViewById(R.id.Tip_Spital);
        TextView nrPaturi=v.findViewById(R.id.Paturi);
        TextView pretConsultatie=v.findViewById(R.id.Pret_Consultatie);
        TextView sectiileSpitalului=v.findViewById(R.id.Sectii);
        TextView notaSpitalului=v.findViewById(R.id.Nota);

        Spital spital=spitale.get(position);
        nume.setText(spital.nume);
        tipulSpitalului.setText(String.valueOf(spital.tipul_spitalului));
        String nr=spital.paturi+" paturi";
        nrPaturi.setText(nr);
        String pret_consultatie=spital.pret_consultatie+" RON";
        pretConsultatie.setText(pret_consultatie);
        String sectiile="";
        for(int i=0;i<spital.sectii.size();i++)
        {
            sectiile=sectiile+spital.sectii.get(i);
            if(i!=spital.sectii.size()-1)
            {
                sectiile=sectiile+", ";
            }
        }
        sectiileSpitalului.setText(sectiile);
        notaSpitalului.setText(String.valueOf(spital.nota));

        return v;
    }
}
