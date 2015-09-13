package com.example.contactsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

       if(getIntent().getExtras() != null) {
            String name = getIntent().getExtras().getString(MainActivity.NAME_KEY);
            Contact contact = getIntent().getExtras().getParcelable(MainActivity.CONTACT_KEY);
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Toast.makeText(this, contact.toString(), Toast.LENGTH_LONG).show();
       } else {

       }
}


    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        findViewById(R.id.save_edit_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.gallery_button)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        // in onCreate or any event where your want the user to
                        // select a file
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("demo", " in on activity result");
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Log.d("demo", "selectedImagePath " + selectedImagePath);
            }
        }
    }

   /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    //alerts pull down menu to select contact once you click the select contact button
    CharSequence[] items = {"Alice Smith", "Cynthia Walter", "Mary George", "Zak Castilo"};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle("Select Contact")
    .setMessage("Are you sure?")
    .setItems(items, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            Log.d("demo", "Selected" + items[which]);
        }
    })

    final AlertDialog alert = builder.create();

   findViewById(R.id.edit_contact_activity).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            alert.show();
        }
    });

}
