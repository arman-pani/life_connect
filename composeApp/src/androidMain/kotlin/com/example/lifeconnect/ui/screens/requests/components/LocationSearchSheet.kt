package com.example.lifeconnect.ui.screens.requests.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.ui.requests.LocationPickerState
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LocationSearchSheetContent(
    sheetState: SheetState,
    state: LocationPickerState, // Receive state from ViewModel
    onConfirm: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSearchConfirmed: () -> Unit
) {
    val isExpanded = sheetState.currentValue == SheetValue.Expanded
    val scope = rememberCoroutineScope()

    // Function to expand the sheet on interaction
    val expandSheet: () -> Unit = {
        scope.launch { sheetState.expand() }
    }

    // Helper function to handle suggestion selection, which also collapses the sheet
    val selectSuggestion: (String) -> Unit = { suggestion ->
        onSearchTextChange(suggestion)
        onSearchConfirmed() // Treat selection as confirming a search for immediate map update
        scope.launch { sheetState.partialExpand() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Set your location", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Drag the map to move the pin", style = MaterialTheme.typography.bodyMedium)
            HorizontalDivider()
        }

        val addressText = if (state.addressLookupFailed) "Address Lookup Failed" else state.currentAddress
        OutlinedTextField(
                value = if (isExpanded) state.searchText else addressText,
                onValueChange = onSearchTextChange,
                leadingIcon = {
                    Icon(
                        Icons.Default.LocationSearching,
                        contentDescription = "Search"
                    )
                },
            maxLines = 1,
            enabled = isExpanded,
            readOnly = !isExpanded, // Make it read-only unless fully expanded
            keyboardActions = KeyboardActions(
                    onSearch = { onSearchConfirmed() }
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = !isExpanded) {
                        // 🔑 ACTION: If not expanded, tap expands the sheet
                        expandSheet()
                    },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    // Ensure the background color looks correct when disabled/minimized
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )


        if (isExpanded) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Placeholder item that can be clicked to demonstrate selection
                items(state.suggestions) { suggestion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectSuggestion(suggestion)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(suggestion, modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
                // If the user has typed text but received no suggestions
                if (state.searchText.isNotBlank() && state.suggestions.isEmpty()) {
                    item {
                        Text("No suggestions found for \"${state.searchText}\"", color = Color.Gray, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }

        CustomFilledButton(
            label = if (isExpanded) "Confirm Search Location" else "Confirm Pinned Location",
            onClick = onConfirm,
            enabled = !state.isLoadingInitialLocation && !state.addressLookupFailed
        )
    }
}