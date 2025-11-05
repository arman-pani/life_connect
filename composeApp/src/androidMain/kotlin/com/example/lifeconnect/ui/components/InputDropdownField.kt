package com.example.lifeconnect.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDropdownField(
        label: String = "Your Blood Group",
        placeholder: String = "Enter group",
        options: List<String> = listOf("Option 1", "Option 2", "Option 3"),
    )
{
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var selectedOption by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        Text(
            label,
            style = MaterialTheme.typography.titleMedium
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = !expanded}
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray),
                placeholder = { Text(placeholder, color = Color.LightGray) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                maxLines = 1,
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.DarkGray,  // Customize as you like
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.DarkGray
                ),
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {Text(option)},
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun InputDropdownFieldPreview(){
    InputDropdownField(

    )
}