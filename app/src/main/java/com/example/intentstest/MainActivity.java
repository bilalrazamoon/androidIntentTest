package com.example.intentstest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Intent intent=null, chooser=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //sendEmail
        findViewById(R.id.sendEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to ={"hafizIntentTestraza@gmail.com","hafizahmedattari@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL,to);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Hi, this was sent from my App");
                intent.putExtra(Intent.EXTRA_TEXT,"Hey whats up, how you doing?");
                intent.setType("message/rfc822");
                chooser=Intent.createChooser(intent,"Send Email");
                startActivity(chooser);
            }
        });

        //launchMap
        findViewById(R.id.launchMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:19.076,72.8777"));
                chooser=Intent.createChooser(intent,"Launch Maps");
                startActivity(chooser);
            }
        });

        //launchMarket
        findViewById(R.id.launchMarket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.google.android.googlequicksearchbox"));
                chooser=Intent.createChooser(intent,"Launch Market");
                startActivity(chooser);
            }
        });

        //sendImage
        findViewById(R.id.sendImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imageUri = Uri.parse("android.resource://com.example.intentstest/drawable/"+R.drawable.ic_launcher);
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,imageUri);
                intent.putExtra(Intent.EXTRA_TEXT,"Hey, i have attached an image");
                chooser=Intent.createChooser(intent,"Send Image");
                startActivity(chooser);
            }
        });

        //sendImages
        findViewById(R.id.sendImages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File pictures=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String[] listOfPictures=pictures.list();
                Uri uri=null;
                ArrayList<Uri> arrayList=new ArrayList<Uri>();
                try {
                    for(String picture: listOfPictures){
                        uri = Uri.parse("file://" + pictures.toString() + "/" + picture);
                        arrayList.add(uri);
                    }
                }
                catch (NullPointerException  e){
                    Log.d("IntentTest",e.getMessage());
                }
                intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,arrayList);
                chooser=Intent.createChooser(intent,"Send Multiple Images");
                startActivity(chooser);
            }
        });

    }

}
