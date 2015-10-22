package com.example.luc.tinder4homework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * Created by Luc on 14-10-2015.
 */
public class CreatePost extends Activity {

    public Uri fileUri;
    public ImageView Huiswerk;
    public Bitmap bitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //Set the corresponding layout.
        setContentView(R.layout.create_post);

        Huiswerk = (ImageView) findViewById(R.id.imageViewHuiswerk);


        Button buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonTakePicture = (Button) findViewById(R.id.buttonTakePicture);
        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // create Intent to take a picture and return control to the calling application
                Intent intentStartCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // start the image capture Intent
                startActivityForResult(intentStartCamera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
//    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                Bundle extras = data.getExtras();
                bitMap = (Bitmap) extras.get("data");
                Huiswerk.setImageBitmap(bitMap);

                //Save the image to a file
                OutputStream stream = null;
                try {
                    stream = new FileOutputStream("/sdcard/test.jpg");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitMap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
                Log.d(MainActivity.class.getSimpleName(),"User Cancelled the image capture");
            } else {
                // Image capture failed, advise user

                Log.d(MainActivity.class.getSimpleName(),"Image capture failed");
            }
        }

//        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Video captured and saved to fileUri specified in the Intent
//                Toast.makeText(this, "Video saved to:\n" +
//                        data.getData(), Toast.LENGTH_LONG).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                // User cancelled the video capture
//            } else {
//                // Video capture failed, advise user
//            }
//        }
    }
}