package com.eduschool.eduschoolapp.GalleryAlbumPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/9/2017.
 */

public class CreatedDate {

    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("album_name")
    @Expose
    private String albumName;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("album_img")
    @Expose
    private String albumImg;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

}