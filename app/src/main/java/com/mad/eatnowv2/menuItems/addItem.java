package com.mad.eatnowv2.menuItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mad.eatnowv2.R;

import java.util.HashMap;
import java.util.Map;

public class addItem extends AppCompatActivity {

    EditText etFoodTitle, etFoodDesc, etExpDate;
    FirebaseFirestore fStore;
    Button btnSubmit;
    ImageView imageSelected;
    Button btnCamera;


    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        imageSelected = findViewById(R.id.imageView2);
        btnCamera = findViewById(R.id.btnCamera);

        etFoodTitle = findViewById(R.id.etFoodTitle);
        etFoodDesc = findViewById(R.id.etFoodDesc);
        etExpDate = findViewById(R.id.etFoodExpDate);

        btnSubmit = findViewById(R.id.btnSubmit);

        fStore = FirebaseFirestore.getInstance();



       btnSubmit.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        String foodName = etFoodTitle.getText().toString().trim();
        String foodDesc = etFoodDesc.getText().toString().trim();
        String expiryDate = etExpDate.getText().toString().trim();


        Map<String, String> Userdata = new HashMap<>();
        Userdata.put("foodName", foodName);
        Userdata.put("foodDesc", foodDesc);
        Userdata.put("expiryDate", expiryDate);


        fStore.collection("UserData").add(Userdata).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent = new Intent(addItem.this, ExpiredItems.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Toast.makeText(addItem.this, "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });


    }


    });


    }
}

















