package com.example.el_fiyyan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Developer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)
    }

    fun btnBack(view: View) {
        val intent = Intent(this@Developer, Profile::class.java)
        startActivity(intent)
    }

}