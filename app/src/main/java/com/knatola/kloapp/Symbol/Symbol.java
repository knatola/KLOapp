package com.knatola.kloapp.Symbol;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by knatola on 20.11.2017.
 */

public class Symbol implements Parcelable{

    private String name;
    private Image image;
    private String pic; //pic = tavu japanilaisittain.

    public Symbol(String name, String pic){
        this.name = name;
        this.pic = pic;
    }

    public static final Parcelable.Creator<Symbol> CREATOR = new Parcelable.Creator<Symbol>() {
        public Symbol createFromParcel(Parcel in) {
            return new Symbol(in);
        }

        public Symbol[] newArray(int size) {

            return new Symbol[size];
        }
    };

        public Symbol(Parcel in) {
            super();
            readFromParcel(in);
        }


    public int describeContents(){
        return 0;
    }
    public void readFromParcel(Parcel in) {
        name = in.readString();
        pic = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pic);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
