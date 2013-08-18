package com.teamcodeflux.devcup.android.festival.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Parcelable {
    public static final String POST_TAG = "post_item_key";

    private int id;
    private String username;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("body")
    private String postBody;

    @JsonProperty("event_id")
    private int eventId;

    @JsonProperty("event_title")
    private String eventTitle;

    private Post() {

    }

    public static final Parcelable.Creator<Post> CREATOR
            = new Parcelable.Creator<Post>() {

        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Post(final Parcel source) {
        readFromParcel(source);
    }

    public static Post buildPost(int id, String name, String imageUrl, String postBody, int eventId) {
        Post post = new Post();
        post.setId(id);
        post.setUsername(name);
        post.setImageUrl(imageUrl);
        post.setPostBody(postBody);
        post.setEventId(eventId);

        return post;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(imageUrl);
        dest.writeString(postBody);
        dest.writeInt(eventId);
        dest.writeString(eventTitle);
    }

    private void readFromParcel(final Parcel source) {
        id = source.readInt();
        username = source.readString();
        imageUrl = source.readString();
        postBody = source.readString();
        eventId = source.readInt();
        eventTitle = source.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}
