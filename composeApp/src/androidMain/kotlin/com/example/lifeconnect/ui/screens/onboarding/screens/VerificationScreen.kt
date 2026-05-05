package com.example.lifeconnect.ui.screens.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.auth.UserViewModel
import com.example.lifeconnect.ui.components.CustomClickableText
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.ui.components.OtpTextField
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    viewModel: UserViewModel = koinViewModel(),
    onSuccess: () -> Unit = {}
){
    val mobileNumber = viewModel.user.value?.mobileNumber ?: ""

    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Verification Code")}
            )
        }
    ){innerPadding ->
        var otpValue by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "We have sent a verification code to your number: $mobileNumber",
                style = MaterialTheme.typography.bodyMedium
            )
            OtpTextField(
                otpText = otpValue,
                onOtpTextChange = { value, otpInputFilled ->
                    otpValue = value
                }
            )
            Text("Resend code in 04.23")
            Spacer(modifier = Modifier.size(24.dp))
            CustomClickableText(
                startingText = "Didn't get the code? ",
                clickableText = "Resend code",
                onClick = {}
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomFilledButton(
                onClick = onSuccess,
                label = "Verify"
            )
        }
    }
}


@Preview
@Composable
fun VerificationScreenPreview(){
    VerificationScreen()
}
