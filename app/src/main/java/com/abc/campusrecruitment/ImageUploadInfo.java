package com.abc.campusrecruitment;

public class ImageUploadInfo {

    public String CompanyName;

    public String imageURL;
    public String description,eligibility,location,website;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String name, String url,String e,String d,String l,String w) {

        this.CompanyName = name;
        this.imageURL= url;
        this.eligibility=e;
        this.description=d;
        this.website=w;
        this.location=l;
    }

    public String getImageName() {
        return CompanyName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String geteligibility() {
        return eligibility;
    }

    public String getdescription() {
        return description;
    }

    public String getlocation() {
        return location;
    }

    public String getwebsite() {
        return website;
    }



}