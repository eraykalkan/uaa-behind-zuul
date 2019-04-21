package com.eray.leadservice.dto;

import java.io.Serializable;

public class TestDTO implements Serializable {

    private String ad;

    private String soyad;

    private String email;

    public TestDTO() {

    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
