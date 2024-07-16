package com.example.myapplication.PhoneNoLogin.Model

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthRepository {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    fun sendVerificationCode(
        number: String,
        activity: Activity,
        message: MutableState<String>,
        verificationID: MutableState<String>,
        isOtpSent: MutableState<Boolean>
    ) {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                message.value = "Verification successful"
                Toast.makeText(activity, "Verification successful..", Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                message.value = "Fail to verify user: \n" + p0.message
                Toast.makeText(activity, "Verification failed..", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationId, p1)
                verificationID.value = verificationId
                isOtpSent.value = true  // Set OTP sent status to true
            }
        }

        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        activity: Activity,
        context: Context,
        message: MutableState<String>,
        onSuccess: () -> Unit
    ) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                message.value = "Verification successful"
                Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
                onSuccess()  // Call the success callback to navigate to home
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(context, "Verification failed: " + (task.exception as FirebaseAuthInvalidCredentialsException).message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
