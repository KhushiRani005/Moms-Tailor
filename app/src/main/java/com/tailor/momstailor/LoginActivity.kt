package com.tailor.momstailor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {
    lateinit var next:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        next = findViewById(R.id.continue_btn)

        next.setOnClickListener {
            var intent = Intent(baseContext,OtpActivity::class.java)
            startActivity(intent)
        }





    }
}

