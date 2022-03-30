package com.example.crimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button buttonregister;
    private EditText regusername,regemail,regpassword,contactNo;
    FirebaseAuth mAuth;
    private Object User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regusername = findViewById(R.id.regusername);
        regemail = findViewById(R.id.regemail);
        regpassword = findViewById(R.id.regpassword);
        contactNo = findViewById(R.id.contactNo);
        TextView btn = findViewById(R.id.btnregister);

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(view ->{
                createUser();
        });



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
                createUser();
            }
        });
    }


    private void createUser() {

            String username = regusername.getText().toString();
            String email = regemail.getText().toString();
            String password = regpassword.getText().toString();
            String phone = contactNo.getText().toString();

        if(username.isEmpty() || username.length()<4)
        {
            showerror(regusername,"Username Not Valid");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showerror(regemail, "Email is not valid");
            regemail.requestFocus();
        }
        else if(password.isEmpty() || password.length()<8)
        {
            showerror(regpassword,"Password must be atleast 8 characters");
            regpassword.requestFocus();
        }
        else if(phone.isEmpty() || phone.length()<10)
        {
            showerror(contactNo,"Number not valid");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this , new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    if (task.isSuccessful()){
                        User user = new User(username,email, password, phone);
                        FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "User registered successfully!!!!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                                }else{
                                    Toast.makeText(RegisterActivity.this, "User registration failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        

                    }else{
                        Toast.makeText(RegisterActivity.this, "User registration failed!!!!", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }
    private void showerror(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}
