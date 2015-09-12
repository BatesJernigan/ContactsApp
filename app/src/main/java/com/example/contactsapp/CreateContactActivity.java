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
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity {

//        if(getIntent().getExtras() != null) {
//            String name = getIntent().getExtras().getString(MainActivity.NAME_KEY);
//            Contact contact = getIntent().getExtras().getParcelable(MainActivity.CONTACT_KEY);
////            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
//            Toast.makeText(this, contact.toString(), Toast.LENGTH_LONG).show();

    private static final int PICTURE_CODE = 1;
    private String selectedImagePath;
    EditText nameEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        nameEditText = (EditText) findViewById(R.id.name_text_view);

        findViewById(R.id.save_contact_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                if (name == null || name.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.CONTACT_KEY, name);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });

        findViewById(R.id.gallery_button)
            .setOnClickListener(new View.OnClickListener() {
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
        Log.d("demo", " in on activity result");
        if (resultCode == RESULT_OK) {
            if (requestCode == PICTURE_CODE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

}
