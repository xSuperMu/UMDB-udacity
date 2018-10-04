
package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductionCompany {

    @SerializedName("id")
    private Long mId;
    @SerializedName("logo_path")
    private Object mLogoPath;
    @SerializedName("name")
    private String mName;
    @SerializedName("origin_country")
    private String mOriginCountry;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getLogoPath() {
        return mLogoPath;
    }

    public void setLogoPath(Object logoPath) {
        mLogoPath = logoPath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOriginCountry() {
        return mOriginCountry;
    }

    public void setOriginCountry(String originCountry) {
        mOriginCountry = originCountry;
    }

}
