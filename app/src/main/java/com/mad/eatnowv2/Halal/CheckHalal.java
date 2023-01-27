package com.mad.eatnowv2.Halal;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;



import com.mad.eatnowv2.R;

public class CheckHalal extends AppCompatActivity {

    WebView WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_halal);
        /*WebView = findViewById(R.id.WebView);
        WebSettings webSettings = WebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebView.setWebViewClient(new callback());
        WebView.loadUrl("https://www.halal.gov.my/v4/index.php?");*/
    }

    /*private class callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent url)
        {
            return false;
        }
    }*/
}

