package com.application.kyucsa


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebActivity : AppCompatActivity() {
    private  lateinit var   webView:WebView
    private   var URL="https://simenya.github.io/kyucsa/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        //Setting up the URL link
        webView = findViewById(R.id.web)
        webView.apply {
            webViewClient= WebViewClient()
            loadUrl(URL)
            settings.javaScriptEnabled=true

        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()

    }
}
