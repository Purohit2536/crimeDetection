package com.example.crimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView button;
    EditText textpassword,textemail;
    Button btnlogin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textemail = findViewById(R.id.textemail);
        textpassword = findViewById(R.id.textpassword);
        button = findViewById(R.id.ButtonSignUp);
        btnlogin = findViewById(R.id.btnlogin);


        button.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this,RegisterActivity.class));
        });

        mAuth = FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(view ->{
            loginUser();
        });

        }

        private void loginUser() {
        String email = textemail.getText().toString();
        String password = textpassword.getText().toString();

        if(TextUtils.isEmpty((email)) )
        {
            textemail.setError("Email cannot be empty");
            textemail.requestFocus();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textemail.setError("Please provide valid email");
            textemail.requestFocus();
            return;
        }else if(TextUtils.isEmpty((password))){
            textpassword.setError("Password cannot be empty");
            textpassword.requestFocus();
        }else{

            mAuth.signInWithEmailAndPassword( email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User Logged in succefully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, HomePage.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this, "User registration failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
