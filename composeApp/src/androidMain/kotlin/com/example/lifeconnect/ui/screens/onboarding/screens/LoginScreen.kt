package com.example.lifeconnect.ui.screens.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.ui.auth.UserViewModel
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(
    viewModel: UserViewModel = koinViewModel(),
    onContinue: () -> Unit = {}
){
    val state by viewModel.user.collectAsState()
    var mobileNumber by rememberSaveable { mutableStateOf(state?.mobileNumber ?: "") }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),

        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Welcome to LifeConnect!",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                text = "Enter your mobile number to register.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(0.25f))
            OutlinedTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = { Text("Mobile Number") },
                placeholder = { Text("Enter your number") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.weight(4f))
            CustomFilledButton(
                onClick = {
                    viewModel.saveMobileNumber(mobileNumber)
                    onContinue()
                },
                label = "Login"
            )
            Spacer(modifier = Modifier.weight(0.25f))
            DividerWithText("OR")
            Spacer(modifier = Modifier.weight(0.25f))
            FilledTonalButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "google_icon",
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Google",
                        style = MaterialTheme.typography.labelLarge.copy(color = Color.Red)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.25f))


        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}

@Composable
fun DividerWithText(text: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        HorizontalDivider(
            modifier = Modifier.weight(1f).height(1.dp),
            color = Color.Gray
        )
        Text(
            text,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f).height(1.dp),
            color = Color.Gray
        )
    }
}