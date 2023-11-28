package com.example.momstailor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.momstailor.ui.theme.MomsTailorTheme

class LoginActivity : ComponentActivity() {
    lateinit var next:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        next = findViewById(R.id.continue_btn)

        next.setOnClickListener {
            var intent:Intent = Intent(baseContext,MainActivity::class.java)
            startActivity(intent)
        }





    }
}

