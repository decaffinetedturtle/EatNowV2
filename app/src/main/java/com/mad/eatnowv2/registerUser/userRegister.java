package com.mad.eatnowv2.registerUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mad.eatnowv2.MainActivity;
import com.mad.eatnowv2.R;
import com.mad.eatnowv2.userDashboard;

import java.util.HashMap;
import java.util.Map;

public class userRegister extends AppCompatActivity {

    private static final String TAG = "userRegister";

    FirebaseAuth mAuth;
    EditText regUser, regPass;
    Button regBtn;

    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        regUser = findViewById(R.id.registerInput);
        regPass = findViewById(R.id.registerPass);

        regBtn = findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();



        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),userDashboard.class));
            finish();;
        }


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerInput = regUser.getText().toString().trim();
                String registerPass = regPass.getText().toString().trim();

                if (TextUtils.isEmpty(registerInput)){
                    regUser.setError("Username field is empty");
                    return;
                }
                if (TextUtils.isEmpty(registerPass)){
                    regPass.setError("Password is field is empty");
                    return;
                }

                if(registerPass.length()< 6){
                    regPass.setError("Password is too short");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(registerInput,registerPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(userRegister.this, "User successfully created", Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference = fStore.collection("users").document(mAuth.getCurrentUser().getUid());
                            Map<String,Object> user = new HashMap<>();
                            user.put("username",registerInput);
                            user.put("password",registerPass);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(userRegister.this, "User profile created", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), userDashboard.class));
                        }
                        else {
                            Toast.makeText(userRegister.this, "Invalid email entered" /*+ task.getException().getMessage()*/, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }





}
