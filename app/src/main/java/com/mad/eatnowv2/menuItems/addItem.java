package com.mad.eatnowv2.menuItems;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

       if (ContextCompat.checkSelfPermission(addItem.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(addItem.this, new String[]{Manifest.permission.CAMERA},101);
       }

       btnCamera.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent, 101);

           }
       });

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageSelected.setImageBitmap(bitmap);
        }
    }
}


















