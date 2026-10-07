package com.trek.customermanager;

public class Customer {

    private final String name;
    private final String phone;
    private final String email;
    private final String province;
    private final String district;

    public Customer(
            String name,
            String phone,
            String email,
            String province,
            String district) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.province = province;
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }
}