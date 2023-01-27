package com.mad.eatnowv2.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.eatnowv2.MainActivity;
import com.mad.eatnowv2.R;

public class userForgot extends AppCompatActivity {

    Button backToLoginBtn;
    Button forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgot);

        backToLoginBtn = findViewById(R.id.backToLoginBtn);

        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userForgot.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}