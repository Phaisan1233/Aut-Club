package com.example.autclub.AppModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Event implements Parcelable {
    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[0];
        }
    };
    private String name;
    private String description;
    private String endtime;
    private String starttime;
    private List<Event> list;

    public Event() {
    }

    public Event(String Name, String descript, String start, String end) throws ParseException {
        this.setDescription(descript);
        this.setName(Name);
        this.setStartime(start);
        this.setEndtime(end);
    }

    protected Event(Parcel in) {
        name = in.readString();
        description = in.readString();
        endtime = in.readString();
        starttime = in.readString();
    }

    public void Eventlist(List<Event> arr) {
        this.list = arr;
    }

    public List<Event> getEventList() {
        return list;

    }

    public String DateFormatter(String format) throws ParseException {
        SimpleDateFormat old = new SimpleDateFormat("yyyy-MM-dd");
        Date ir = old.parse(format.substring(0, 10));
        SimpleDateFormat j = new SimpleDateFormat("dd-MM-yyyy");
        String y = j.format(ir);
        SimpleDateFormat New = new SimpleDateFormat("HH:mm:ss");
        Date time = New.parse(format.substring(11, 19));
        SimpleDateFormat n = new SimpleDateFormat("HH:mm:ss");
        String t = n.format(time);
        String niceformat = y.concat(" " + t);
        return niceformat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) throws ParseException {

        this.endtime = DateFormatter(endtime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartime() {
        return starttime;
    }

    public void setStartime(String starttime) throws ParseException {
        this.starttime = DateFormatter(starttime);
    }

    @Override
    public String toString() {
        return "\nEvent Name:" + this.getName() + "\n" + "Description: " + this.getDescription() + "\n" + "Start Time: " + this.getStartime() + "\n" + "End Time: " + this.getEndtime();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(endtime);
        dest.writeString(starttime);
    }


}
