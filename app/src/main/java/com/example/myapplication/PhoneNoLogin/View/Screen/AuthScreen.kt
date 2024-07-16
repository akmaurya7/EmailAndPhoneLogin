package com.example.myapplication.PhoneNoLogin.View

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.PhoneNoLogin.ViewModel.AuthViewModel
import com.example.myapplication.ui.theme.greenColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(authViewModel: AuthViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold{ innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = authViewModel.phoneNumber.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { authViewModel.phoneNumber.value = it },
                    placeholder = { Text(text = "Enter your phone number") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (authViewModel.phoneNumber.value.isEmpty()) {
                            Toast.makeText(context, "Please enter phone number..", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            authViewModel.sendVerificationCode(context)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Generate OTP", modifier = Modifier.padding(8.dp))
                }
                if (authViewModel.isOtpSent.value) {
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = authViewModel.otp.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { authViewModel.otp.value = it },
                        placeholder = { Text(text = "Enter your otp") },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            if (authViewModel.otp.value.isEmpty()) {
                                Toast.makeText(context, "Please enter otp..", Toast.LENGTH_SHORT).show()
                            } else {
                                authViewModel.verifyOtp(context) {
                                    navController.navigate("home")
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Verify OTP", modifier = Modifier.padding(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = authViewModel.message.value,
                    style = TextStyle(
                        color = greenColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
