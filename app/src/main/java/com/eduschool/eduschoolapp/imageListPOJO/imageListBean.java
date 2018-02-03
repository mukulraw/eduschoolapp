package com.eduschool.eduschoolapp.imageListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/1/2017.
 */

public class imageListBean {


    @SerializedName("gallery_list")
    @Expose
    private List<GalleryList> galleryList = null;

    public List<GalleryList> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<GalleryList> galleryList) {
        this.galleryList = galleryList;
    }

}
