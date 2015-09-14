package com.example.contactsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DeleteContactActivity extends Activity {

    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        final ArrayList<Contact> clist = getIntent().getParcelableArrayListExtra(MainActivity.CONTACT_KEY);

        final TextView name = (TextView) findViewById(R.id.Name_field);
        final TextView phone = (TextView) findViewById(R.id.Phone_field);
        final TextView email = (TextView) findViewById(R.id.Email_field);
        final ImageView avatarPhoto = (ImageView) findViewById(R.id.imageView2);

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
                        avatarPhoto.setImageURI(Uri.parse(clist.get(which).getAvatarPhoto()));
                        index = which;
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
                name.setText("");
                phone.setText("");
                email.setText("");
                Toast.makeText(DeleteContactActivity.this, "Deleted Contact", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra(MainActivity.Index_value,index);
                setResult(RESULT_OK,intent);
                finish();
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
