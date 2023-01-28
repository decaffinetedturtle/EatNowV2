package com.mad.eatnowv2.itemLists.unExpiredStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mad.eatnowv2.R;

public class updateUnExpired extends AppCompatActivity {

    EditText etFoodName, etFoodDesc, etExpDate;
    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_un_expired);


        etFoodName = findViewById(R.id.updateFoodName);
        etFoodDesc = findViewById(R.id.updateFoodDesc);
        etExpDate = findViewById(R.id.updateFoodDate);

        btnUpdate = findViewById(R.id.updateBtn);


    }
}