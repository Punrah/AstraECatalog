package com.dealerhondabali.astraecatalog.persistence;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Startup on 5/30/17.
 */

public class MerkKompetitor implements Parcelable {

    public String merk;
    public ArrayList<HargaKompetitor> hargaKompetitorList;



    public MerkKompetitor(){
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(merk);
        dest.writeList(hargaKompetitorList);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public MerkKompetitor createFromParcel(Parcel in) {
            return new MerkKompetitor(in);
        }

        public MerkKompetitor[] newArray(int size) {
            return new MerkKompetitor[size];
        }
    };

    // "De-parcel object
    public MerkKompetitor(Parcel in) {
        merk = in.readString();
        hargaKompetitorList = in.readArrayList(MerkKompetitor.class.getClassLoader());
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public ArrayList<HargaKompetitor> getHargaKompetitorList() {
        return hargaKompetitorList;
    }

    public void setHargaKompetitorList(ArrayList<HargaKompetitor> hargaKompetitorList) {
        this.hargaKompetitorList = hargaKompetitorList;
    }
}