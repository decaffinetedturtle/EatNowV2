package com.mad.eatnowv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userDashboard extends AppCompatActivity {
        private Button btn_store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        button = (Button) findViewById(R.id.btn_store);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStore(); // this is the method that will open the store activity

            }
        });
    }
    public void openStore(){
        Intent intent = new Intent(this, Store.class);
        startActivity(intent);
    }

    private void startActivity(Intent intent) {
    }
}