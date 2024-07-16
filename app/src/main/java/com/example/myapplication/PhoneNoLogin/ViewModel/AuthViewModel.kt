package com.example.myapplication.PhoneNoLogin.ViewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.PhoneNoLogin.Model.AuthRepository
import com.google.firebase.auth.PhoneAuthProvider

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    val phoneNumber = mutableStateOf("")
    val otp = mutableStateOf("")
    val verificationID = mutableStateOf("")
    val message = mutableStateOf("")
    val isOtpSent = mutableStateOf(false)  // New state to track OTP sent status

    fun sendVerificationCode(context: Context) {
        val number = "+91${phoneNumber.value}"
        authRepository.sendVerificationCode(number, context as Activity, message, verificationID, isOtpSent)
    }

    fun verifyOtp(context: Context, onSuccess: () -> Unit) {
        val credential = PhoneAuthProvider.getCredential(verificationID.value, otp.value)
        authRepository.signInWithPhoneAuthCredential(credential, context as Activity, context, message, onSuccess)
    }
}
