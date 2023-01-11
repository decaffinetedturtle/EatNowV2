package com.mad.eatnowv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

import com.mad.eatnowv2.menuItems.ExpiredItems;
import com.mad.eatnowv2.menuItems.addItem;
import com.mad.eatnowv2.menuItems.unExpiredItems;
import com.mad.eatnowv2.menuItems.userSettings;

public class userDashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView unExpiredItems, expiredItems, userSettings, scanCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);


        unExpiredItems = (CardView) findViewById(R.id.unexpiredCard);
        expiredItems = (CardView) findViewById(R.id.expiredCard);
        userSettings = (CardView) findViewById(R.id.userCard);
        scanCard = (CardView) findViewById(R.id.scanCard);


        unExpiredItems.setOnClickListener(this);
        expiredItems.setOnClickListener(this);
        userSettings.setOnClickListener(this);
        scanCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        android.content.Intent i;

        switch (v.getId()) {
            case R.id.unexpiredCard :
                i = new android.content.Intent(this, unExpiredItems.class); startActivity(i); break;
            case R.id.expiredCard :
                i = new android.content.Intent(this, ExpiredItems.class); startActivity(i); break;
            case R.id.userCard :
                i = new android.content.Intent(this, userSettings.class); startActivity(i); break;
            case R.id.scanCard :
                i = new android.content.Intent(this, addItem.class); startActivity(i); break;
            default:break;
        }


    }
}