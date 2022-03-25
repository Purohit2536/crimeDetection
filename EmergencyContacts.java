package com.example.crimeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmergencyContacts extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private TextView calltext,calltext1,calltext2,calltext3;
    private Button callbutton,callBtn1,callBtn2,callBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        calltext1 = findViewById(R.id.calltext1);
        calltext2 = findViewById(R.id.calltext2);
        calltext3 = findViewById(R.id.calltext3);
        calltext = findViewById(R.id.calltext);

        callbutton = findViewById(R.id.callBtn);
        callBtn1 = findViewById(R.id.callBtn1);
        callBtn2 = findViewById(R.id.callBtn2);
        callBtn3 = findViewById(R.id.callBtn3);
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallButton();
            }
        });

        callBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallButton1();
            }
        });

        callBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallButton2();
            }
        });

        callBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallButton3();
            }
        });
    }

    private void CallButton() {
        String number = calltext.getText().toString();
        if (number.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(EmergencyContacts.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(EmergencyContacts.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL );
            } else {
                String dial = "tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton1() {
        String number = calltext1.getText().toString();
        if (number.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(EmergencyContacts.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(EmergencyContacts.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL );
            } else {
                String dial = "tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }

    private void CallButton2() {
        String number = calltext2.getText().toString();
        if (number.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(EmergencyContacts.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(EmergencyContacts.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL );
            } else {
                String dial = "tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }
    private void CallButton3() {
        String number = calltext3.getText().toString();
        if (number.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(EmergencyContacts.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(EmergencyContacts.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL );
            } else {
                String dial = "tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
    }
}
