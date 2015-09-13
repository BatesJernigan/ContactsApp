package com.example.contactsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeleteContactActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        final ArrayList<Contact> clist = new ArrayList<>();

        Contact c = new Contact("alice","0897767678","arjun@unc.edu");
        Contact c1 = new Contact("alice1","08977676781","arjun1@unc.edu");
        Contact c2 = new Contact("alice2","08977676782","arjun2@unc.edu");
        Contact c3 = new Contact("alice3","08977676783","arjun3@unc.edu");


        clist.add(c);
        clist.add(c1);
        clist.add(c2);
        clist.add(c3);

        final TextView name = (TextView) findViewById(R.id.Name_field);
        final TextView phone = (TextView) findViewById(R.id.Phone_field);
        final TextView email = (TextView) findViewById(R.id.Email_field);

        int clist_size = clist.size();

        final CharSequence[] items = new CharSequence[clist_size];

        for(int i=0; i< clist.size(); i ++) {
            items[i] = clist.get(i).getName();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Contact")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("demo", "selected" + " " + items[which]);
                        name.setText(clist.get(which).getName());
                        phone.setText(clist.get(which).getPhone());
                        email.setText(clist.get(which).getEmail());
                    }
                });

        final AlertDialog simpleA = builder.create();
        findViewById(R.id.SelectContactBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleA.show();
            }
        });

        findViewById(R.id.DeleteContactBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send information to main activity inorder to delete contact
            }
        });

        findViewById(R.id.CancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
