package com.example.contactsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayContactsActivity extends AppCompatActivity {

    public int clickcount = 0;

    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<Contact> items = new ArrayList<>();
        Contact c3 = new Contact("alice3","08977676783","arjun3@unc.edu");
        Contact c2 = new Contact("alice2","08977676782","arjun2@unc.edu");
        Contact c1 = new Contact("alice1","08977676781","arjun1@unc.edu");
        Contact c = new Contact("alice","0897767678","arjun@unc.edu");
        items.add(c);
        items.add(c1);
        items.add(c2);
        items.add(c3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        final TextView name = (TextView) findViewById(R.id.DummyName);
        final TextView phone = (TextView) findViewById(R.id.DummyNumber);
        final TextView email = (TextView) findViewById(R.id.DummyEmail);


        findViewById(R.id.NextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickcount = clickcount+1;
                if(clickcount < items.size()) {
                    name.setText(items.get(clickcount).getName());
                    phone.setText(items.get(clickcount).getPhone());
                    email.setText(items.get(clickcount).getEmail());
                }
                else {
                    clickcount = 0;
                    name.setText(items.get(clickcount).getName());
                    phone.setText(items.get(clickcount).getPhone());
                    email.setText(items.get(clickcount).getEmail());
                }

            }
        });

        

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"johne@uncc.edu"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "body");
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Choose Email App.."));
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:123456789"));
                startActivity(intent);
            }
        });


    }
}
