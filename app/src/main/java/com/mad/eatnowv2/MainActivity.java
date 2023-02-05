package com.mad.eatnowv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mad.eatnowv2.forgetPassword.retrieveEmailForgetPassword;
import com.mad.eatnowv2.registerUser.userRegister;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button btnLogin, btnRegister, ForgotPasswordBtn, fingerPrintBtn;
    EditText etUsername, etPassword, etapa;

    FirebaseAuth fAuth;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //light sensor
        // Register the light sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // button find view by ids
        btnLogin = findViewById(R.id.loginBtn);
        btnRegister = findViewById(R.id.registerBtn);
        ForgotPasswordBtn = findViewById(R.id.ForgotPasswordBtn);
        fingerPrintBtn = findViewById(R.id.fingerPrintBtn);

        fAuth = FirebaseAuth.getInstance();

        // edit text find view by ids
        etUsername = findViewById(R.id.usernameET);
        etPassword = findViewById(R.id.passwordET);

        // set on click listener login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get username and password
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)){
                    etUsername.setError("Username field is empty");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    etPassword.setError("Password is field is empty");
                    return;
                }

                if(password.length()< 6){
                    etPassword.setError("Password is too short");
                    return;
                }

                fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), userDashboard.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // check if username and password are empty
/*                if (username.isEmpty() || password.isEmpty()) {
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
                }*/
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lightLevel = event.values[0];
        if (lightLevel > 100) {
            Toast.makeText(MainActivity.this, "Light level is high", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Light level is low", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    //do nothing
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}