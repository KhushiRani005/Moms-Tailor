package com.tailor.momstailor

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class OtpActivity : ComponentActivity() {
    private lateinit var otp:OtpEditText
    private lateinit var submit:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        otp = findViewById(R.id.otp_edit_text)
        submit = findViewById(R.id.submit_btn)


        submit.setOnClickListener {
            Toast.makeText(baseContext,otp.text,Toast.LENGTH_SHORT).show()
        }

    }
}