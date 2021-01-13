package com.example.el_fiyyan

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            val username = username_input_login.text.toString()
            val password = password_input_login.text.toString()
            if (username.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Please Insert Username and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(username == "admin" || password == "admin123"){
                val progressDialog = ProgressDialog(this,
                        R.style.Theme_MaterialComponents_Light_Dialog)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Loading...")
                progressDialog.show()
                val intent = Intent (this,Dashboard::class.java)
                startActivity(intent)
                finish()
            }

        }

    }
}