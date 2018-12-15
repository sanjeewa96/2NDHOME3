package com.example.sk.a2ndhome;

public class DepartmentDetails {

    String id;
    String name;
    String address;
    String phonenumber;
    String rent;


    public DepartmentDetails()
    {

    }

    public DepartmentDetails(String id, String name, String address, String phonenumber, String rent) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.rent = rent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getRent() {
        return rent;
    }
}
