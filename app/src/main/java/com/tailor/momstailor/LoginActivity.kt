package com.tailor.momstailor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private var verificationId: String? = null
    lateinit var next:Button
    lateinit var error:TextView
    lateinit var phoneNum:EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        next = findViewById(R.id.continue_btn)
        phoneNum = findViewById(R.id.phone_number_edit)
        error = findViewById(R.id.error)

        auth = FirebaseAuth.getInstance()


        next.setOnClickListener {
            val phoneNumber = phoneNum.text.toString()
            var intent = Intent(baseContext,FillOTPActivity::class.java)
            intent.putExtra("phone",phoneNumber)
            startActivity(intent)
        }

    }

    }
