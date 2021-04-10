package com.example.rdvmanager;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class RDV implements Parcelable {
    private long id;
    private String title;
    private String date;
    private String time;
    private String address;
    private String contact;
    private int phoneNum;
    private boolean state;

    public RDV()
    {}
    
    public RDV(String pTitle, String pDate, String pTime, String pAddress, String pContact, int pPhone)
    {
        this.title = pTitle;
        this.date = pDate;
        this.time = pTime;
        this.address = pAddress;
        this.contact = pContact;
        this.phoneNum = pPhone;
        this.state = false;
    }

    public RDV(long pId, String pTitle, String pDate, String pTime, String pAddress, String pContact, int pPhone) {
        this.id = pId;
        this.title = pTitle;
        this.date = pDate;
        this.time = pTime;
        this.address = pAddress;
        this.contact = pContact;
        this.phoneNum = pPhone;
        this.state = false;
    }

    long getId()    { return this.id;}

    void setId(long id) {this.id = id;}

    String getTitle()
    {
        return this.title;
    }

    void setTitle(String pTitle)
    {
        this.title = pTitle;
    }

    String getDate()
    {
        return this.date;
    }

    void setDate(String pDate){
        this.date = pDate;
    }

    String getTime()
    {
        return this.time;
    }

    void setTime(String pTime){
        this.time = pTime;
    }

    String getAddress()
    {
        return this.address;
    }

    void setAddress(String pAddress){
        this.address = pAddress;
    }

    String getContact()
    {
        return this.contact;
    }

    void setContact(String pContact){
        this.date = pContact;
    }

    int getPhoneNumber()
    {
        return this.phoneNum;
    }

    void setPhoneNumber(int pPhoneNumber){
        this.phoneNum = pPhoneNumber;
    }

    boolean getState()
    {
        return this.state;
    }

    void setStateTrue (){
        this.state = true;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(address);
        dest.writeString(contact);
        dest.writeInt(phoneNum);
        dest.writeBoolean(state);
    }
    public static final Parcelable.Creator<RDV> CREATOR = new Parcelable.Creator<RDV>(){
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public RDV createFromParcel(Parcel parcel) {
            return new RDV(parcel);
        }
        @Override
        public RDV[] newArray(int size) {
            return new RDV[size];
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public RDV(Parcel parsel){
        id=parsel.readLong();
        title=parsel.readString();
        date=parsel.readString();
        time=parsel.readString();
        address=parsel.readString();
        contact=parsel.readString();
        phoneNum=parsel.readInt();
        state=parsel.readBoolean();
    }
}
