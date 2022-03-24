package com.example.crimedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button buttonregister;
    private EditText regusername,regemail,regpassword,regphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regusername = findViewById(R.id.regusername);
        regemail = findViewById(R.id.regemail);
        regpassword = findViewById(R.id.regpassword);
        regphone = findViewById(R.id.regphone);
        TextView btn = findViewById(R.id.btnregister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonregister = findViewById(R.id.buttonregister);
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCrendentials();
            }
        });
    }
    private void checkCrendentials() {

        String username = regusername.getText().toString();
        String email = regemail.getText().toString();
        String password = regpassword.getText().toString();
        String phone = regphone.getText().toString();

        if(username.isEmpty() || username.length()<4)
        {
            showerror(regusername,"Username Not Valid");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showerror(regemail, "Email is not valid");
        }
        else if(password.isEmpty() || password.length()<8)
        {
            showerror(regpassword,"Password must be atleast 8 characters");
        }
        else if(phone.isEmpty() || phone.length()<10)
        {
            showerror(regphone,"Number not valid");
        }
        else
        {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        }
    private void showerror(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}
