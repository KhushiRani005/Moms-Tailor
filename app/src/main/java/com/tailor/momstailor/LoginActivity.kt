package com.tailor.momstailor

import android.annotation.SuppressLint
import android.content.Intent
import java.util.concurrent.TimeUnit
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider

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
            sendOtp(phoneNumber)
//            var intent = Intent(baseContext,OtpActivity::class.java)
//            startActivity(intent)
        }

    }

    private fun sendOtp(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout duration
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)           // OnVerificationStateChangedCallbacks
            .build()
        Toast.makeText(baseContext,""+phoneNumber,Toast.LENGTH_SHORT).show()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1. Instant verification. In some cases, the phone number can be instantly
            //    verified without needing to send or enter the verification code.
            // 2. Auto-retrieval. On some devices, Google Play services can automatically
            //    detect the incoming verification SMS and perform verification without user action.
            Toast.makeText(baseContext,">> "+credential,Toast.LENGTH_SHORT).show()

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification,
            // like an empty phone number or an invalid country code.
            // Handle the error here.
            Toast.makeText(baseContext,"failed : "+e,Toast.LENGTH_SHORT).show()
            error.text = e.message

        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number.
            // Save the verification ID and resending token to use them later.
            this@LoginActivity.verificationId = verificationId
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Verification successful, handle the signed in user
                    val user = task.result?.user
                    Toast.makeText(baseContext,"user "+user,Toast.LENGTH_SHORT).show()
                    // Do something with the user, for example, navigate to the next screen
                } else {
                    Toast.makeText(baseContext,"failed",Toast.LENGTH_SHORT).show()
                    // Verification failed, handle the error
                }
            }
    }
}

