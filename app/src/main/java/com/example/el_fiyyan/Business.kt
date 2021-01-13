package com.example.el_fiyyan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Business : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)
    }

    fun btnBack2(view: View) {
        val intent = Intent(this@Business, Profile::class.java)
        startActivity(intent)
    }

}