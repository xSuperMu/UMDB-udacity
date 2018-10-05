package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductionCountry implements Serializable {
    @SerializedName("iso_3166_1")
    @Expose
    private String iso_3166_1;
    @SerializedName("name")
    @Expose
    private String name;

    public ProductionCountry() {
    }

    public ProductionCountry(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getIso31661() {
        return iso_3166_1;
    }

    public void setIso31661(String iso31661) {
        iso_3166_1 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductionCountry{" +
                "iso_3166_1='" + iso_3166_1 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
