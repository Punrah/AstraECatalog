package com.dealerhondabali.astraecatalog.persistence;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Startup on 5/30/17.
 */

public class HargaKompetitor implements Parcelable {

    public String jenis;
    public String harga;



    public HargaKompetitor(){
    }

    public HargaKompetitor(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.harga = data[0];
        this.jenis = data[1];
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.harga,
                this.jenis});
    }
    public static final Creator CREATOR = new Creator() {
        public Kredit createFromParcel(Parcel in) {
            return new Kredit(in);
        }

        public Kredit[] newArray(int size) {
            return new Kredit[size];
        }
    };

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}