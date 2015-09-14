package com.example.contactsapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayContactsActivity extends AppCompatActivity {

    private int index = 0;
    private ArrayList<Contact> items;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        items = getIntent().getParcelableArrayListExtra(MainActivity.CONTACT_KEY);
        final TextView name = (TextView) findViewById(R.id.Name_Field);
        final TextView phone = (TextView) findViewById(R.id.Phone_Field);
        final TextView email = (TextView) findViewById(R.id.Email_Field);
        final ImageView avatarPhoto = (ImageView) findViewById(R.id.photo);

        name.setText(items.get(0).getName());
        phone.setText(items.get(0).getPhone());
        email.setText(items.get(0).getEmail());
        avatarPhoto.setImageURI(Uri.parse(items.get(0).getAvatarPhoto()));


        findViewById(R.id.NextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index < items.size()) {
                    name.setText(items.get(index).getName());
                    phone.setText(items.get(index).getPhone());
                    email.setText(items.get(index).getEmail());
                    avatarPhoto.setImageURI(Uri.parse(items.get(index).getAvatarPhoto()));
                } else {
                    index = 0; // reset index
                    name.setText(items.get(index).getName());
                    phone.setText(items.get(index).getPhone());
                    email.setText(items.get(index).getEmail());
                    avatarPhoto.setImageURI(Uri.parse(items.get(index).getAvatarPhoto()));
                }
            }
        });

        findViewById(R.id.PrevBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if (index < 0) {
                    index = items.size() - 1; // reset index
                    name.setText(items.get(index).getName());
                    phone.setText(items.get(index).getPhone());
                    email.setText(items.get(index).getEmail());
                    avatarPhoto.setImageURI(Uri.parse(items.get(index).getAvatarPhoto()));
                } else {
                    name.setText(items.get(index).getName());
                    phone.setText(items.get(index).getPhone());
                    email.setText(items.get(index).getEmail());
                    avatarPhoto.setImageURI(Uri.parse(items.get(index).getAvatarPhoto()));
                }
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Choose Email App.."));
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phone.getText().toString()));
                startActivity(intent);
            }
        });

        findViewById(R.id.firstContactbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(items.get(0).getName());
                phone.setText(items.get(0).getPhone());
                email.setText(items.get(0).getEmail());
                avatarPhoto.setImageURI(Uri.parse(items.get(0).getAvatarPhoto()));
            }
        });

        findViewById(R.id.lastContactbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(items.get(items.size()-1).getName());
                phone.setText(items.get(items.size()-1).getPhone());
                email.setText(items.get(items.size()-1).getEmail());
                avatarPhoto.setImageURI(Uri.parse(items.get(items.size()-1).getAvatarPhoto()));
            }
        });

        findViewById(R.id.finishBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
