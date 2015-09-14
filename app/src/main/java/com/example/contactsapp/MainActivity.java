package com.example.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static int CREATE_CODE = 100;
    final static String CONTACT_KEY = "CONTACT";
    final static int delete_code = 111;
    final static String Index_value = "INDEX";

    final static  ArrayList<Contact> contactsList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CREATE_CODE) {
            if (resultCode == RESULT_OK) {
                Contact contact = (Contact) data.getExtras().get(CONTACT_KEY);
                contactsList.add(contact);
            } else if(resultCode == RESULT_CANCELED) {
                Log.d("demo", "No value recieved");
            }
        }
        if(requestCode == delete_code) {
            if (resultCode == RESULT_OK) {
                int in = (int) data.getExtras().get(Index_value);
                contactsList.remove(in);
                Log.d("demo","removed");
            } else if(resultCode == RESULT_CANCELED) {
                Log.d("demo", "No value recieved");
            }
        }
    }


    public void changeToCreateActivity(View view) {
        Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
        startActivityForResult(intent, CREATE_CODE);
    }

    public void changeToEditActivity(View view) {
        Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
        startActivity(intent);
    }

    public void changeToDeleteActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DeleteContactActivity.class);
        startActivityForResult(intent, delete_code);
    }

    public void changeToDisplayActivity(View view) {

        if(MainActivity.contactsList.isEmpty()){
            Toast.makeText(MainActivity.this, "Please create a contact first", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(MainActivity.this, DisplayContactsActivity.class);
            startActivity(intent);
        }
    }

    public void changeToFinishActivity(View view) {
        finish();
    }
}
