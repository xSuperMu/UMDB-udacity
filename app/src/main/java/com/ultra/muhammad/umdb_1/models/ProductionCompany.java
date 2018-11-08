package com.ultra.muhammad.umdb_1.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class ProductionCompany implements Serializable {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("logo_path")
    @Expose
    private Object logo_path;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin_country")
    @Expose
    private String origin_country;

    public ProductionCompany() {
    }

    public ProductionCompany(Long id, Object logo_path, String name, String origin_country) {
        this.id = id;
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getLogoPath() {
        return logo_path;
    }

    public void setLogoPath(Object logoPath) {
        logo_path = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return origin_country;
    }

    public void setOriginCountry(String originCountry) {
        origin_country = originCountry;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProductionCompany{" +
                "id=" + id +
                ", logo_path=" + logo_path +
                ", name='" + name + '\'' +
                ", origin_country='" + origin_country + '\'' +
                '}';
    }
}
