package com.example.contactsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditContactActivity extends AppCompatActivity {

    private static final int PICTURE_CODE = 1;

    private EditText nameEditText, phoneEditText, emailEditText;
    private ImageButton avatarButton;
    private String selectedImage;
    private int index = 0;
    private ArrayList<Contact> contactList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        contactList = getIntent().getParcelableArrayListExtra(MainActivity.CONTACT_KEY);
        nameEditText = (EditText) findViewById(R.id.edit_contact_name);
        phoneEditText = (EditText) findViewById(R.id.edit_contact_phone);
        emailEditText = (EditText) findViewById(R.id.edit_contact_email);
        avatarButton = (ImageButton) findViewById(R.id.edit_content_avatar);

        final CharSequence[] items = new CharSequence[contactList.size()];

        for(int i=0; i< contactList.size(); i ++) {
            items[i] = contactList.get(i).getName();
        }

        findViewById(R.id.save_edit_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                if ( selectedImage == null || selectedImage.length() == 0) {
                    selectedImage = getString(R.string.default_blank_avatar_path);
                }

                if (isTextBad(name, phone, email)) {
                    setResult(RESULT_CANCELED);
                } else {
                    Toast.makeText(EditContactActivity.this, "Edited Contact", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.Index_value, index);

                    intent.putExtra(MainActivity.CONTACT_KEY,
                        new Contact(name, phone, email, selectedImage));
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });

        findViewById(R.id.cancel_edit_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        avatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), PICTURE_CODE);
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Contact")
            .setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("demo", "selected" + " " + items[which]);
                    nameEditText.setText(contactList.get(which).getName(), TextView.BufferType.EDITABLE);
                    phoneEditText.setText(contactList.get(which).getPhone(), TextView.BufferType.EDITABLE);
                    emailEditText.setText(contactList.get(which).getEmail(), TextView.BufferType.EDITABLE);
                    avatarButton.setImageURI(Uri.parse(contactList.get(which).getAvatarPhoto()));
                    index = which;
                }
            });
        final AlertDialog alert = builder.create();

        findViewById(R.id.edit_contact_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICTURE_CODE) {
                selectedImage = data.getData().toString();
                avatarButton.setImageURI(data.getData());
            }
        }
    }

    public boolean isTextBad(String name, String phone, String email) {
        boolean flag = false;
        if(name == null || name.length() == 0 || name.length() > 50) {
            flag = true;
            Toast.makeText(this, "The contact name can't be blank or exceed 50 characters",
                    Toast.LENGTH_LONG).show();
        }
        if(phone == null || phone.length() == 0 || phone.length() > 10) {
            flag = true;
            Toast.makeText(this, "The phone number can't be blank or exceed 10 characters",
                    Toast.LENGTH_LONG).show();
        }

        if(!email.matches("^([a-z]|[A-Z]|\\d*)*@[a-z]*(\\.com)") || email == null ||
                email.length() == 0) {
            flag = true;
            Toast.makeText(this, "Email address can't be blank and needs to be valid",
                    Toast.LENGTH_LONG).show();
        }

        return flag;
    }
}
