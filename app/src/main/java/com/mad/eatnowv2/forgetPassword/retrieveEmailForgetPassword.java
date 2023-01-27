package com.mad.eatnowv2.forgetPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.mad.eatnowv2.R;

public class retrieveEmailForgetPassword extends AppCompatActivity {

    Button retrieveEmailBtn;
    EditText emailInputLayout;

    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_email_forget_password);

        retrieveEmailBtn = findViewById(R.id.retrieveEmailBtn);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        Auth = FirebaseAuth.getInstance();

        retrieveEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.sendPasswordResetEmail(emailInputLayout.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(retrieveEmailForgetPassword.this, "Email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(retrieveEmailForgetPassword.this, "Error! Email not sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                );
        }  ;
    });


        /*retrieveEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(retrieveEmailForgetPassword.this, userForgot.class);
                startActivity(intent);
            }
        });*/

    }
}