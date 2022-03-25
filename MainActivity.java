package com.example.crimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textpassword,textusername;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textusername = findViewById(R.id.textusername);
        textpassword = findViewById(R.id.textpassword);

        TextView btn = findViewById(R.id.btnSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this, RegisterActivity.class);
            }
        });

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCrendentials();
            }
        });
    }
    private void checkCrendentials() {
        String username = textusername.getText().toString();
        String password = textpassword.getText().toString();

        if(username.isEmpty() || username.length()<4)
        {
            showerror(textusername,"Username Not Valid");
        }
        else if(password.isEmpty() || password.length()<8)
        {
            showerror(textpassword,"Password must be atleast 8 characters");
        }
        else
        {
            Intent i = new Intent(MainActivity.this, HomePage.class);
            startActivity(i);
        }
    }
    private void showerror(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}
