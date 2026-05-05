package com.example.lifeconnect.ui.screens.profileSetup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.components.InputDropdownField
import com.example.lifeconnect.ui.components.InputTextField

@Composable
fun PersonalInfoColumn(){
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.Red, shape = CircleShape)
                    .align(alignment = Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "profile",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text("Personal Information", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }
        InputTextField(
            label = "Your Name",
            placeholder = "Enter name",
            value = ""
        )
        InputTextField(
            label = "Your Email",
            placeholder = "Enter email",
            value = ""
        )
        InputDropdownField(
            label = "Select Group",
            placeholder = "Blood Group"
        )
        InputDropdownField(
            label = "Select Country",
            placeholder = "Country"
        )
        InputDropdownField(
            label = "Select City",
            placeholder = "City"
        )
    }
}

@Preview
@Composable
fun PersonalInfoColumnPreview(){
    PersonalInfoColumn()
}