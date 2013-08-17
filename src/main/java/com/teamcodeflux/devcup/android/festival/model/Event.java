package com.teamcodeflux.devcup.android.festival.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    public static final String EVENT_TAG = "event_item_key";

    private String title;
    private String description;
    private String address;
    private String contactNo;
    private String imageUrl;
    private String organizer;
    private Double longitude;
    private Double latitude;

    private Event() {

    }

    public static Event buildEvent(String title, String description, String address, String contactNo, String imageUrl, String organizer, Double longitude, Double latitude) {
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setAddress(address);
        event.setContactNo(contactNo);
        event.setImageUrl(imageUrl);
        event.setOrganizer(organizer);
        event.setLongitude(longitude);
        event.setLatitude(latitude);

        return event;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(contactNo);
        dest.writeString(imageUrl);
        dest.writeString(organizer);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }

    private void readFromParcel(final Parcel source) {
        title = source.readString();
        description = source.readString();
        address = source.readString();
        contactNo = source.readString();
        imageUrl = source.readString();
        organizer = source.readString();
        longitude = source.readDouble();
        latitude = source.readDouble();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
