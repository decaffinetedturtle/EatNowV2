package com.mad.eatnowv2.QrScanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.eatnowv2.R;

public class QRCodePage extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_page);

        btn = findViewById(R.id.btnQr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QRCodePage.this, QrCodeScanner.class);
                startActivity(intent);
            }
        });
    }
}