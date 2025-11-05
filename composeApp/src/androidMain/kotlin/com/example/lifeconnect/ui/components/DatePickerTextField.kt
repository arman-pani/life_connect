package com.example.lifeconnect.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTextField(){
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false)}
    val datePickerState = rememberDatePickerState()

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            "Date of Birth",
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            readOnly = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray),
            placeholder = { Text("Select date", color = Color.LightGray) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select Date",
                    tint = Color.Gray,
                    modifier = Modifier.clickable { showDatePicker = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.DarkGray
            ),
        )

        if (showDatePicker){
            DatePickerDialog(
                modifier = Modifier.padding(horizontal = 16.dp),
                onDismissRequest = {showDatePicker = false},
                confirmButton = {
                    TextButton(onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val formatted = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                .format(Date(millis))
                            selectedDate = formatted
                        }
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {showDatePicker = false}) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}