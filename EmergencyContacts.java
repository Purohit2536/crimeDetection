package com.example.crimedetection;

import androidx.annotation.NonNull;
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
    private TextView calltxt;
    private Button callbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);
        calltxt = findViewById(R.id.calltext);
        callbutton = findViewById(R.id.callBtn);
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallButton();
            }
        });
    }

    private void CallButton() {
        String number = calltxt.getText().toString();
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
