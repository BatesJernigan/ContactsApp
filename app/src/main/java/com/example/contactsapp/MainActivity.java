package com.example.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static String NAME_KEY = "NAMEBack";

    private List<Contact> contactsList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeToCreateActivity(View view) {
        Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
        intent.putExtra(NAME_KEY, "Bob Smith");
        startActivity(intent);
    }

    public void changeToEditActivity(View view) {
        Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
        startActivity(intent);
    }

    public void changeToDeleteActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DeleteContactActivity.class);
        startActivity(intent);
    }

    public void changeToDisplayActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayContactsActivity.class);
        startActivity(intent);
    }

    public void changeToFinishActivity(View view) {
        finish();
    }
}
