package com.teamcodeflux.devcup.android.festival.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    public static final String POST_TAG = "post_item_key";

    private String name;
    private String postBody;
    private String imageUrl;

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

    public static Post buildPost(String name, String postBody) {
        Post post = new Post();
        post.setName(name);
        post.setPostBody(postBody);

        return post;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(postBody);
        dest.writeString(imageUrl);
    }

    private void readFromParcel(final Parcel source) {
        name = source.readString();
        postBody = source.readString();
        imageUrl = source.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
