package com.mad.eatnowv2.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.eatnowv2.R;

public class retrieveEmailForgetPassword extends AppCompatActivity {

    Button retrieveEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_email_forget_password);

        retrieveEmailBtn = findViewById(R.id.retrieveEmailBtn);

        retrieveEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(retrieveEmailForgetPassword.this, userForgot.class);
                startActivity(intent);
            }
        });

    }
}