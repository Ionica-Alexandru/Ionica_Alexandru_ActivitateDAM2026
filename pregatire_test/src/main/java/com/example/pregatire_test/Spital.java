package com.example.pregatire_test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

enum tip {Public,Privat}
public class Spital implements Parcelable {
    String nume;
    tip tipul_spitalului;
    int paturi;
    float pret_consultatie;
    List<String> sectii;
    double nota;

    public Spital(String nume, tip tipul_spitalului, int paturi, float pret_consultatie, List<String> sectii, double nota) {
        this.nume = nume;
        this.tipul_spitalului = tipul_spitalului;
        this.paturi = paturi;
        this.pret_consultatie = pret_consultatie;
        this.sectii = sectii;
        this.nota = nota;
    }


    protected Spital(Parcel in) {
        nume = in.readString();
        paturi = in.readInt();
        pret_consultatie = in.readFloat();
        sectii = in.createStringArrayList();
        nota = in.readDouble();
        tipul_spitalului= tip.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeInt(paturi);
        dest.writeFloat(pret_consultatie);
        dest.writeStringList(sectii);
        dest.writeDouble(nota);
        dest.writeString(tipul_spitalului.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Spital> CREATOR = new Creator<Spital>() {
        @Override
        public Spital createFromParcel(Parcel in) {
            return new Spital(in);
        }

        @Override
        public Spital[] newArray(int size) {
            return new Spital[size];
        }
    };
}
