package com.teamcodeflux.devcup.android.festival.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Parcelable {
    public static final String EVENT_TAG = "event_item_key";

    private int id;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("thumb")
    private String thumbnail;

    private String title;
    private String description;
    private String location;
    private Double longitude;
    private Double latitude;

    private Event() {

    }

    public static final Parcelable.Creator<Event> CREATOR
            = new Parcelable.Creator<Event>() {

        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public Event(final Parcel source) {
        readFromParcel(source);
    }

    public static Event buildEvent(int id, String imageUrl, String thumbnail, String title, String description, String location, Double longitude, Double latitude) {
        Event event = new Event();
        event.setId(id);
        event.setImageUrl(imageUrl);
        event.setThumbnail(thumbnail);
        event.setTitle(title);
        event.setDescription(description);
        event.setLocation(location);
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
        dest.writeInt(id);
        dest.writeString(imageUrl);
        dest.writeString(thumbnail);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }

    private void readFromParcel(final Parcel source) {
        id = source.readInt();
        imageUrl = source.readString();
        thumbnail = source.readString();
        title = source.readString();
        description = source.readString();
        location = source.readString();
        longitude = source.readDouble();
        latitude = source.readDouble();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
