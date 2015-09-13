package com.example.contactsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayContactsActivity extends AppCompatActivity {

    public int nextcount = 0;
    public int prevcount = nextcount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);
        final ArrayList<Contact> items = new ArrayList<>();
        final TextView name = (TextView) findViewById(R.id.Name_Field);
        final TextView phone = (TextView) findViewById(R.id.Phone_Field);
        final TextView email = (TextView) findViewById(R.id.Email_Field);

        Contact c3 = new Contact("alice3","08977676783","arjun3@unc.edu","");
        Contact c2 = new Contact("alice2","08977676782","arjun2@unc.edu","");
        Contact c1 = new Contact("alice1","08977676781","arjun1@unc.edu","");
        Contact c = new Contact("alice","0897767678","arjun@unc.edu","");
        items.add(c);
        items.add(c1);
        items.add(c2);
        items.add(c3);

        name.setText(items.get(0).getName());
        phone.setText(items.get(0).getPhone());
        email.setText(items.get(0).getEmail());

        findViewById(R.id.NextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextcount = nextcount + 1;
                if (nextcount < items.size()) {
                    name.setText(items.get(nextcount).getName());
                    phone.setText(items.get(nextcount).getPhone());
                    email.setText(items.get(nextcount).getEmail());
                } else {
                    nextcount = 0;
                    name.setText(items.get(nextcount).getName());
                    phone.setText(items.get(nextcount).getPhone());
                    email.setText(items.get(nextcount).getEmail());
                }
            }
        });

        findViewById(R.id.PrevBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevcount = prevcount - 1;
                if (prevcount < 0) {
                    prevcount = items.size() - 1;
                    name.setText(items.get(prevcount).getName());
                    phone.setText(items.get(prevcount).getPhone());
                    email.setText(items.get(prevcount).getEmail());
                } else {
                    name.setText(items.get(prevcount).getName());
                    phone.setText(items.get(prevcount).getPhone());
                    email.setText(items.get(prevcount).getEmail());
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
            }
        });

        findViewById(R.id.lastContactbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(items.get(items.size()-1).getName());
                phone.setText(items.get(items.size()-1).getPhone());
                email.setText(items.get(items.size()-1).getEmail());
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
