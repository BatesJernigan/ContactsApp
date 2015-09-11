package com.example.contactsapp;

import android.net.Uri;

/**
 * Created by batesjernigan on 9/11/15.
 */
public class Contact {

    private String name;
    private String phone;
    private String email;
    private Uri avatarPhoto;

    public Contact(String name, String phone, String email /*, Uri avatarPhoto*/) {
        this.name = name;
        this.phone = phone;
        this.email = email;
//        this.avatarPhoto = avatarPhoto;
    }

    public Uri getAvatarPhoto() {
        return avatarPhoto;
    }

    public void setAvatarPhoto(Uri avatarPhoto) {
        this.avatarPhoto = avatarPhoto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact{" +
//            "avatarPhoto=" + avatarPhoto +
            ", name='" + name + '\'' +
            ", phone=" + phone +
            ", email='" + email + '\'' +
            '}';
    }
}
