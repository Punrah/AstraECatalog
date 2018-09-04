package com.dealerhondabali.astraecatalog.persistence;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Startup on 5/30/17.
 */

public class Show implements Parcelable {

    public String gambar;
    public String link;
    public String label;


    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Show(){
    }

    public Show(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.gambar = data[0];
        this.link = data[1];
        this.label = data[2];
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.gambar,
                this.link,
                this.label});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Kredit createFromParcel(Parcel in) {
            return new Kredit(in);
        }

        public Kredit[] newArray(int size) {
            return new Kredit[size];
        }
    };
}