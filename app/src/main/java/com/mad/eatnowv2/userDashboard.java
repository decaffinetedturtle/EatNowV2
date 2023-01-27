package com.mad.eatnowv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mad.eatnowv2.menuItems.ExpiredItems;
import com.mad.eatnowv2.menuItems.addItem;
import com.mad.eatnowv2.menuItems.unExpiredItems;

import com.mad.eatnowv2.Halal.CheckHalal;

public class userDashboard extends AppCompatActivity implements View.OnClickListener {

    TextView dashboardUsername;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userID;

    private CardView unExpiredItems, expiredItems, btn_halal, scanCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userID = fauth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                dashboardUsername.setText(documentSnapshot.getString("username"));
            }
        });


        unExpiredItems = (CardView) findViewById(R.id.unexpiredCard);
        expiredItems = (CardView) findViewById(R.id.expiredCard);
        btn_halal = (CardView) findViewById(R.id.userCard);
        scanCard = (CardView) findViewById(R.id.scanCard);


        unExpiredItems.setOnClickListener(this);
        expiredItems.setOnClickListener(this);
        btn_halal.setOnClickListener(this);
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
                i = new android.content.Intent(this, CheckHalal.class); startActivity(i); break;
            case R.id.scanCard :
                i = new android.content.Intent(this, addItem.class); startActivity(i); break;
            default:break;
        }


    }
}