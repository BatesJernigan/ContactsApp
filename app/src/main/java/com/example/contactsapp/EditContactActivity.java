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
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {

    private static final int PICTURE_CODE = 1;

    private EditText nameEditText, phoneEditText, emailEditText;
    private ImageButton avatarButton2;
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


                if (isTextBad(name, phone, email)) {
                    setResult(RESULT_CANCELED);
                } else {
                    ;
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

    //alerts pull down menu to select contact once you click the select contact button
    CharSequence[] items = {"Alice Smith", "Cynthia Walter", "Mary George", "Zak Castilo"};

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Contact")

                .setItems(items , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Log.d("demo", "Selected" + items[which]);

                    }
                });
        return builder.create();

        findViewById(R.id.edit_contact_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                alert.show();
            }
        });

    }


}
