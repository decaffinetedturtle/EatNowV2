package com.mad.eatnowv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.eatnowv2.forgetPassword.retrieveEmailForgetPassword;
import com.mad.eatnowv2.registerUser.userRegister;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister, ForgotPasswordBtn, fingerPrintBtn;
    EditText etUsername, etPassword, etapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button find view by ids
        btnLogin = findViewById(R.id.loginBtn);
        btnRegister = findViewById(R.id.registerBtn);
        ForgotPasswordBtn = findViewById(R.id.ForgotPasswordBtn);
        fingerPrintBtn = findViewById(R.id.fingerPrintBtn);

        // edit text find view by ids
        etUsername = findViewById(R.id.usernameET);
        etPassword = findViewById(R.id.passwordET);

        // set on click listener login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get username and password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // check if username and password are empty
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // check if username and password are correct
                    if (username.equals("admin") && password.equals("admin")) {
                        // if correct, go to home page
                        Intent intent = new Intent(MainActivity.this, userDashboard.class);
                        startActivity(intent);
                    } else {
                        // if incorrect, show error message
                        Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, userRegister.class);
                startActivity(intent);
            }
        });

        ForgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, retrieveEmailForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    private void checkBioMetricSupported() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK
                | BiometricManager.Authenticators.BIOMETRIC_STRONG)){
            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this, "App can authenticate using biometrics", Toast.LENGTH_SHORT).show();
                enableButton(true);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "No biometric features available on this device", Toast.LENGTH_SHORT).show();
                enableButton(false);

                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Biometric features are currently unavailable", Toast.LENGTH_SHORT).show();
                enableButton(false);

                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "The user hasn't associated any biometric credentials with their account", Toast.LENGTH_SHORT).show();
                enableButton(false, true);
                break;
        }
    }

    void enableButton(Boolean enable){
        fingerPrintBtn.setEnabled(enable);
    }
    void enableButton(Boolean enable, Boolean enroll){
        enableButton(enable);
        if(!enroll) return;
        Intent enrollIntent = new Intent(android.provider.Settings.ACTION_BIOMETRIC_ENROLL);
        enrollIntent.putExtra(android.provider.Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.BIOMETRIC_WEAK);
        startActivity(enrollIntent);
        }
    }