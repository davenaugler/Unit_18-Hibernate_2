package com.coderscampus.Unit_18_Hibernate_2.domain;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    private Long userId;
    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;
    @Column(length = 200)
    private String addressLine1;
    @Column(length = 200)
    private String getAddressLine2;
    @Column(length = 100)
    private String city;
    @Column(length = 100)
    private String region;
    @Column(length = 100)
    private String country;
    @Column(length = 15)
    private String zipCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getGetAddressLine2() {
        return getAddressLine2;
    }

    public void setGetAddressLine2(String getAddressLine2) {
        this.getAddressLine2 = getAddressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "userId=" + userId +
                ", user=" + user +
                ", addressLine1='" + addressLine1 + '\'' +
                ", getAddressLine2='" + getAddressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
