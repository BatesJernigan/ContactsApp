/**
 * Assignment: HW_2
 * Created by Thurman Bates Jernigan, Arjun Kabballi Srinivasa, Tempestt Swinson
 * File Name: Contact.java
 */

package com.example.contactsapp;

import android.os.Parcel;
import android.os.Parcelable;
public class Contact implements Parcelable {

    private String name, phone, email, avatarPhoto;

    public Contact(String name, String phone, String email , String avatarPhoto) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.avatarPhoto = avatarPhoto;
    }

    public String getAvatarPhoto() {
        return avatarPhoto;
    }

    public void setAvatarPhoto(String avatarPhoto) {
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

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(avatarPhoto);
    }

    // order matters here follow order of writeParcel
    private Contact(Parcel in) {
        this.name = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.avatarPhoto = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Contact{" +
            "avatarPhoto=" + avatarPhoto +
            ", name='" + name + '\'' +
            ", phone=" + phone +
            ", email='" + email + '\'' +
            '}';
    }
}
