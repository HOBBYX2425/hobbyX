package com.example.hobbyx.model;

import android.net.Uri;

import java.net.URI;
import java.util.List;

public class GalleryImages {
    public List<Uri> pickUri;


    public GalleryImages() {
    }

    public GalleryImages(List<Uri> pickUri) {
        this.pickUri = pickUri;

    }

    public List<Uri> getPickUri() {
        return pickUri;
    }

    public void setPickUri(List<Uri> pickUri) {
        this.pickUri = pickUri;
    }



}

