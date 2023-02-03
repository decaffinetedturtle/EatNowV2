package com.mad.eatnowv2.itemLists.unExpiredStuff;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mad.eatnowv2.R;
import com.mad.eatnowv2.itemLists.foodGetter;

public class updateUnExpired extends AppCompatActivity{

    EditText etFoodName, etFoodDesc, etExpDate;
    Button btnUpdate, btnExpired;

    private FirebaseFirestore fstore;

    private foodGetter food;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_un_expired);

        food = (foodGetter) getIntent().getSerializableExtra("food");

        fstore = FirebaseFirestore.getInstance();

        etFoodName = findViewById(R.id.updateFoodName);
        etFoodDesc = findViewById(R.id.updateFoodDesc);
        etExpDate = findViewById(R.id.updateFoodDate);

        btnUpdate = findViewById(R.id.updateBtn);
        btnExpired = findViewById(R.id.expiredBtn);

        etFoodName.setText(food.getFoodName());
        etFoodDesc.setText(food.getFoodDesc());
        etExpDate.setText(food.getExpiryDate());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();
                finish();

            }
        });

        btnExpired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(updateUnExpired.this);
                builder.setTitle("Are you sure?");
                builder.setMessage("Food will be moved to expired list");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveFood(fstore.collection("UserData").document(food.getFoodId()),
                                fstore.collection("expiredFood").document(food.getFoodId()));
                        Toast.makeText(updateUnExpired.this, "Food moved to expired list", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });


    }




    private void updateFood() {
        String foodName = etFoodName.getText().toString().trim();
        String foodDesc = etFoodDesc.getText().toString().trim();
        String expDate = etExpDate.getText().toString().trim();

        foodGetter foodObj = new foodGetter(foodName, foodDesc, expDate);

        fstore.collection("UserData").document(food.getFoodId())
               .update(
                        "expiryDate", foodObj.getExpiryDate(),
                        "foodDesc", foodObj.getFoodDesc(),
                        "foodName", foodObj.getFoodName()
                )

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(updateUnExpired.this, "Food Updated", Toast.LENGTH_SHORT).show();
                        Log.e("WORKKKK", "PLEASE FUCKING WORKKK");


                    }
                });


    }



    public void moveFood(DocumentReference fromPath, final DocumentReference toPath) {
        fromPath.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        toPath.set(document.getData())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                        fromPath.delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error deleting document", e);
                                                    }
                                                });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error writing document", e);
                                    }
                                });
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

}