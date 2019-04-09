package edu.fsu.cs.mobile.bikeapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Bike implements Parcelable {
    private String make;
    private String model;
    private String type;
    private String color;
    private String wheel_size;
    private String tire_width;
    private String valve;


    public Bike(String make, String model, String type, String color, String wheel_size, String tire_width, String valve)
    {
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.wheel_size = wheel_size;
        this.tire_width = tire_width;
        this.valve = valve;
    }

    public String getMake() {return this.make;}
    public String getModel() {return this.model;}
    public String getType() {return this.type;}
    public String getColor() {return this.color;}
    public String getWheel_size() {return this.wheel_size;}
    public String getTire_width() {return this.tire_width;}
    public String getValve() {return this.valve;}

    public void setWheel_size(String size) {this.wheel_size = wheel_size;}
    public void setTire_width(String width) {this.tire_width = tire_width;}
    public void setValve(String Valve) {this.valve = valve;}



    public String toString() {return this.make + this.model;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.make);
        dest.writeString(this.model);
        dest.writeString(this.type);
        dest.writeString(this.color);
        dest.writeString(this.wheel_size);
        dest.writeString(this.tire_width);
        dest.writeString(this.valve);
    }

    protected Bike(Parcel in) {
        this.make = in.readString();
        this.model = in.readString();
        this.type = in.readString();
        this.color = in.readString();
        this.wheel_size = in.readString();
        this.tire_width = in.readString();
        this.valve = in.readString();
    }

    public static final Creator<Bike> CREATOR = new Creator<Bike>() {
        @Override
        public Bike createFromParcel(Parcel source) {
            return new Bike(source);
        }

        @Override
        public Bike[] newArray(int size) {
            return new Bike[size];
        }
    };
}
