package com.mad.eatnowv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.eatnowv2.forgetPassword.retrieveEmailForgetPassword;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister, ForgotPasswordBtn;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button find view by ids
        btnLogin = findViewById(R.id.loginBtn);
        btnRegister = findViewById(R.id.registerBtn);
        ForgotPasswordBtn = findViewById(R.id.ForgotPasswordBtn);

        //edit text find view by ids
        etUsername = findViewById(R.id.usernameET);
        etPassword = findViewById(R.id.passwordET);

        //set on click listener login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username and password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //check if username and password are empty
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }else{
                    //check if username and password are correct
                    if(username.equals("admin") && password.equals("admin")){
                        //if correct, go to home page
                        Intent intent = new Intent(MainActivity.this, userDashboard.class);
                        startActivity(intent);
                    }else{
                        //if incorrect, show error message
                        Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, userRegister.class);
                startActivity(intent);
            }
        });


        ForgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, retrieveEmailForgetPassword.class);
                startActivity(intent);
            }
        });



    }
}