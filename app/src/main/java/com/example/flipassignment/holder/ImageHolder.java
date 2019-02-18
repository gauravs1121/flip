package com.example.flipassignment.holder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageHolder {

    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("large")
    @Expose
    private String large;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
