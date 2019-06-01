package com.example.autclub.MainController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.autclub.R;

public class ViewActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        webView = (WebView) findViewById(R.id.webView1);
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSey-w0-h0EaWeZ8mal5h7cifu0sLL080rJMbjrdrIg3mIiWow/viewform?vc=0&c=0&w=1");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);

    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }

}
