package com.example.contactsapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity {

//        if(getIntent().getExtras() != null) {
//            String name = getIntent().getExtras().getString(MainActivity.NAME_KEY);
//            Contact contact = getIntent().getExtras().getParcelable(MainActivity.CONTACT_KEY);
////            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
//            Toast.makeText(this, contact.toString(), Toast.LENGTH_LONG).show();

    private static final int PICTURE_CODE = 1;

    private EditText nameEditText, phoneEditText, emailEditText;
    private ImageButton avatarButton;
    private String selectedImage;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        nameEditText = (EditText) findViewById(R.id.name_text_view);
        phoneEditText = (EditText) findViewById(R.id.phone_text_view);
        emailEditText = (EditText) findViewById(R.id.email_text_view);
        avatarButton = (ImageButton) findViewById(R.id.gallery_button);

        findViewById(R.id.save_contact_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();

                if ( selectedImage == null || selectedImage.length() == 0) {
                    selectedImage =
                        "android.resource://com.example.contactsapp/drawable/avatar_blank";
                }

                if (isTextBad(name, phone, email)) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.CONTACT_KEY,
                        new Contact(name, phone, email, selectedImage));
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });

        findViewById(R.id.cancel_contact_activity).setOnClickListener(new View.OnClickListener() {
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
