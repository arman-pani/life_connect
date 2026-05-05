package com.example.lifeconnect.ui.screens.requests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.ui.components.DatePickerTextField
import com.example.lifeconnect.ui.components.InputDropdownField
import com.example.lifeconnect.ui.components.InputTextField
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun CreateRequestScreen(
    navController: NavController,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text("Create Blood Request") },

            )
        } ,
    ) { innerPadding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            InputTextField(
                label = "Patient Name",
                placeholder = "Enter name",
                value = ""
            )
            InputDropdownField(
                label = "Select Group",
                placeholder = "Blood Group"
            )
            InputTextField(
                label = "Contact Person Name",
                placeholder = "Enter name",
                value = ""
            )
            InputTextField(
                label = "Contact Number",
                placeholder = "Enter number",
                value = ""
            )
            InputTextField(
                label = "Location",
                placeholder = "Enter location",
                value = ""
            )

            InputTextField(
                label = "Hospital Name",
                readOnly = true,
                placeholder = "Enter hospital",
                value = ""
            )

            Spacer(modifier = Modifier.weight(1f))
            CustomFilledButton(
                onClick = {},
                label = "Request for Blood"
            )
        }

    }

    }
