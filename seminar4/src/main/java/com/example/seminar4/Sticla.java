package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

enum tip {Plata, Minerala, De_Izvor ,Sifon}
public class Sticla implements Parcelable, Serializable
{
    String brand;
    tip tip_apa;
    int volum;
    float pret;
    List<String> magazine=new ArrayList<>();//magazine care o comercializeaza;
    float nota;

    public Sticla(String brand, tip tip_apa, int marime, float pret, List<String> magazine, float nota)
    {
        this.brand=brand;
        this.tip_apa=tip_apa;
        this.volum=marime;
        this.pret=pret;
        this.magazine=magazine;
        this.nota=nota;
    }

    protected Sticla(Parcel in) {
        brand = in.readString();
        volum = in.readInt();
        pret = in.readFloat();
        magazine = in.createStringArrayList();
        nota = in.readFloat();
        tip_apa = tip.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeInt(volum);
        dest.writeFloat(pret);
        dest.writeStringList(magazine);
        dest.writeFloat(nota);
        dest.writeString(tip_apa.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sticla> CREATOR = new Creator<Sticla>() {
        @Override
        public Sticla createFromParcel(Parcel in) {
            return new Sticla(in);
        }

        @Override
        public Sticla[] newArray(int size) {
            return new Sticla[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
//            return "Brand: "+brand+"\nTipul apei: "+tip_apa.toString()+"\nVolum: "+volum+"ml\nPret: "+Math.round(pret * 100.0f) / 100.0f
//                    +" RON\nMagazine care o comercializeaza: " +magazine.stream().collect(Collectors.joining(", "))
//                    +"\nNota: "+Math.round(nota * 100.0f) / 100.0f;
        return "Brand: "+brand+"\nTipul apei: "+tip_apa.toString()+"\nVolum: "+volum;
    }
}
