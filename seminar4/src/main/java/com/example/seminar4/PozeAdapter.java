package com.example.seminar4;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PozeAdapter extends BaseAdapter {

    List<ImagineDomeniu> imagini=null;
    private Context ctx;
    private int resursaLayout;

    public PozeAdapter(List<ImagineDomeniu> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View v=inflater.inflate(resursaLayout,viewGroup,false);

        ImageView imv=v.findViewById(R.id.imagine);
        TextView desc=v.findViewById(R.id.textViewDesc);

        ImagineDomeniu imagineDomeniu= (ImagineDomeniu) getItem(position);

        imv.setImageBitmap(imagineDomeniu.imagine);
        desc.setText(imagineDomeniu.textAfisat);

        return v;
    }
}
