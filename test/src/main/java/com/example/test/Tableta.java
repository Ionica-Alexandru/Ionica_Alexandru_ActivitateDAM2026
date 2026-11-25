package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

enum sist_op {Android,iOS,Windows}
public class Tableta implements Parcelable {
    int idTableta;
    String model;
    int spatiuMemoriel;
    sist_op sistem_de_operare;

    public Tableta(int idTableta, String model, int spatiuMemoriel, sist_op sistem_de_operare) {
        this.idTableta = idTableta;
        this.model = model;
        this.spatiuMemoriel = spatiuMemoriel;
        this.sistem_de_operare = sistem_de_operare;
    }

    protected Tableta(Parcel in) {
        idTableta = in.readInt();
        model = in.readString();
        spatiuMemoriel = in.readInt();
        sistem_de_operare= sist_op.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idTableta);
        dest.writeString(model);
        dest.writeInt(spatiuMemoriel);
        dest.writeString(String.valueOf(sistem_de_operare));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tableta> CREATOR = new Creator<Tableta>() {
        @Override
        public Tableta createFromParcel(Parcel in) {
            return new Tableta(in);
        }

        @Override
        public Tableta[] newArray(int size) {
            return new Tableta[size];
        }
    };

    @Override
    public String toString() {
        return "ID: " + idTableta +
                " | Model: " + model +
                " | Stocare: " + spatiuMemoriel + " GB" +
                " | OS: " + sistem_de_operare;
    }
}
