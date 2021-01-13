package com.example.el_fiyyan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    fun btnDeveloper(view: View) {
        val intent = Intent(this@Profile, Developer::class.java)
        startActivity(intent)
    }

    fun btnBusiness(view: View) {
        val intent = Intent(this@Profile, Business::class.java)
        startActivity(intent)
    }

    fun btnBack3(view: View) {
        val intent = Intent(this@Profile, Dashboard::class.java)
        startActivity(intent)
    }

}