package com.example.el_fiyyan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    fun btnLogout(view: View) {
        val intent = Intent(this@Dashboard, MainActivity::class.java)
        startActivity(intent)
    }

    fun btnEmail(view: View) {
        val intent = Intent(this@Dashboard, Email::class.java)
        startActivity(intent)
    }

    fun btnProfile(view: View) {
        val intent = Intent(this@Dashboard, Profile::class.java)
        startActivity(intent)
    }

    fun btnProduct(view: View) {
        val intent = Intent(this@Dashboard, ProductActivity::class.java)
        startActivity(intent)
    }

    fun btnPenjualan(view: View) {
        val intent = Intent(this@Dashboard, Penjualan::class.java)
        startActivity(intent)
    }

}