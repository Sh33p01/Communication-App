package com.example.xisdapp;


public class messages {

    private String userEmail;
    private String Themessage;
    private String datetime;

    public messages() {

    }

    public messages(String userEmail, String themessage, String datetime) {
        this.userEmail = userEmail;
        Themessage = themessage;
        this.datetime = datetime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getThemessage() {
        return Themessage;
    }

    public void setThemessage(String themessage) {
        Themessage = themessage;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}

