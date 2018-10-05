package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpokenLanguage implements Serializable {
    @SerializedName("iso_639_1")
    @Expose
    private String iso_639_1;
    @SerializedName("name")
    @Expose
    private String name;

    public SpokenLanguage() {
    }

    public SpokenLanguage(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso6391() {
        return iso_639_1;
    }

    public void setIso6391(String iso6391) {
        iso_639_1 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpokenLanguage{" +
                "iso_639_1='" + iso_639_1 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
