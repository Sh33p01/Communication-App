package com.example.xisdapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.Loginpassword);
        login = findViewById(R.id.logbtn);

        progressBar =  findViewById(R.id.progressB);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(Login.this , reg.class);

                startActivity(f);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String loginEmail = email.getText().toString();
                String loginPassword = password.getText().toString();

                if(loginEmail.isEmpty())
                {
                    email.setError("Staff Email is required");
                    email.requestFocus();
                    return;
                }
                if(loginPassword.isEmpty())
                {
                    password.setError("Staff password is required");
                    password.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Login success", Toast.LENGTH_LONG).show();

                            Intent h = new Intent(Login.this, Homepage.class);
                            startActivity(h);
                            finish();
                            progressBar.setVisibility(View.GONE);

                        }
                        else
                        {
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });
            }
        });



    }

}