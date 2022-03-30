package com.example.crimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Crime_Activity extends AppCompatActivity {

    Button Murderbtn,Violencebtn,Friringbtn,Robberybtn,Bomb_Blast,Kidnappingbtn,Burglarybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.crime);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuitem) {

                switch (menuitem.getItemId()){
                    case R.id.crime:
                        return;

                    case R.id.home: startActivity(new Intent(getApplicationContext(),HomePage.class));
                        overridePendingTransition(0,0);
                        return;

                    case R.id.addimage: startActivity(new Intent(getApplicationContext(),ImageActivity.class));
                        overridePendingTransition(0,0);
                        return;
                }
            }
        });

        Murderbtn = findViewById(R.id.Murderbtn);
        Murderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Crime_Activity.this,MurderActivity.class);
                startActivity(i);
            }
        });

        Murderbtn = findViewById(R.id.Violencebtn);
        Murderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Crime_Activity.this,ViolenceActivity.class);
                startActivity(i);
            }
        });

        Murderbtn = findViewById(R.id.Firingbtn);
        Murderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Crime_Activity.this,FiringActivity.class);
                startActivity(i);
            }
        });

        Murderbtn = findViewById(R.id.IdentityTheft);
        Murderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Crime_Activity.this,BombBlastActivity.class);
                startActivity(i);
            }
        });

        Murderbtn = findViewById(R.id.Kidnappingbtn);
        Murderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Crime_Activity.this,KidnappingActivity.class);
                startActivity(i);
            }
        });

        Murderbtn = findViewById(R.id.Burglarybtn);
        Murderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Crime_Activity.this,BurglaryActivity.class);
                startActivity(i);
            }
        });
    }
}
