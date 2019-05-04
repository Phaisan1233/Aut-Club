package com.example.clubs;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Parcelable {
    private String name;
    private String description;
    private String endtime;
    private String starttime;


    public Event(){

    }

    public Event(String Name,String descript,String start,String end) throws ParseException {
        this.setDescription(descript);
        this.setName(Name);
        this.setStartime(start);
        this.setEndtime(end);
    }

    public Event(String Name,String start,String end) throws ParseException {
        this.setDescription("");
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

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };



    public String DateFormatter(String format) throws ParseException {
        SimpleDateFormat old=new SimpleDateFormat("yyyy-MM-dd");
        Date ir=old.parse(format.substring(0,10));
        SimpleDateFormat j=new SimpleDateFormat("dd-MM-yyyy");
        String y=j.format(ir);
        SimpleDateFormat New=new SimpleDateFormat("HH:mm:ss");
        Date time=New.parse(format.substring(11,19));
        SimpleDateFormat n=new SimpleDateFormat("HH:mm:ss");
        String t=n.format(time);
        String niceformat=y.concat(" "+t);
        return niceformat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndtime(String endtime) throws ParseException {
        /*SimpleDateFormat old=new SimpleDateFormat("yyyy-MM-dd");
        Date ir=old.parse(endtime.substring(0,10));
        SimpleDateFormat j=new SimpleDateFormat("dd-MM-yyyy");
        String y=j.format(ir);
      SimpleDateFormat New=new SimpleDateFormat("HH:mm:ss");
      Date time=New.parse(endtime.substring(11,19));
      SimpleDateFormat n=new SimpleDateFormat("HH:mm:ss");
      String t=n.format(time);
        String nice=y.concat(" "+t);*/

        this.endtime = DateFormatter(endtime);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartime(String starttime) throws ParseException {
      /* SimpleDateFormat old=new SimpleDateFormat("yyyy-MM-dd");
        Date ir=old.parse(starttime.substring(0,10));
        SimpleDateFormat j=new SimpleDateFormat("dd-MM-yyyy");
        String y=j.format(ir);
        SimpleDateFormat New=new SimpleDateFormat("HH:mm:ss");
        Date time=New.parse(starttime.substring(11,19));
        SimpleDateFormat n=new SimpleDateFormat("HH:mm:ss");
        String t=n.format(time);
        String nice=y.concat(" "+t);*/
      String nice="";
        this.starttime = DateFormatter(starttime);
    }

    public String getDescription() {
        return description;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getName() {
        return name;
    }

    public String getStartime() {
        return starttime;
    }


    @Override
    public String toString() {
        return "Event Name:"+this.getName()+"\n"+"Description: "+this.getDescription()+"\n"+"Start Time: "+this.getStartime()+"\n"+"End Time: "+this.getEndtime();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(endtime);
        dest.writeString(starttime);

    }
}
