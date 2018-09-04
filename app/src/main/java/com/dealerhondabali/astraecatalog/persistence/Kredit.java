package com.dealerhondabali.astraecatalog.persistence;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Startup on 5/30/17.
 */

public class Kredit implements Parcelable {

    public String nama;
    public String cash;
    public String credit;
    public String bonus;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
    public Kredit(){
    }

    public Kredit(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.nama = data[0];
        this.cash = data[1];
        this.credit = data[2];
        this.bonus = data[3];
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.nama,
                this.cash,
                this.credit,this.bonus});
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