package com.abc.campusrecruitment;

/**
 * Created by Belal on 4/15/2018.
 */

public class User {
    public String name;
    public String email;
    public String phone;
    public String id;
    public String cgpa;
    public String branch;


    public User(){

    }

    User(String name, String email, String phone, String id, String cgpa, String branch) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.cgpa = cgpa;
        this.branch = branch;

    }

    public String getCgpa() {
        return cgpa;
    }

    public String getBranch() {
        return branch;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
