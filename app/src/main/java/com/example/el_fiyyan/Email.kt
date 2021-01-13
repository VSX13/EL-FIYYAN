package com.example.el_fiyyan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient

class Email : AppCompatActivity() {
    private var webView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        webView = findViewById<View>(R.id.email) as WebView
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl("https://mail.google.com/")
        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}