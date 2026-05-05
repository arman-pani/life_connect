package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.lifeconnect.ui.auth.UserViewModel
import com.example.lifeconnect.ui.components.DatePickerTextField
import com.example.lifeconnect.ui.components.InputDropdownField
import com.example.lifeconnect.ui.components.InputTextField
import com.example.lifeconnect.ui.screens.profile.components.DefaultAppBar
import com.example.lifeconnect.utils.localDateToString
import com.example.lifeconnect.utils.stringToLocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview()
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = koinViewModel(),
    onBackClick: () -> Unit = {}
){
    val state = viewModel.formState.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            DefaultAppBar("Edit Profile", onBackClick )
        },
        containerColor = Color(0xFFF9F9F9)
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding).fillMaxSize().verticalScroll(scrollState ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(56.dp)
            ) {
                AsyncImage(
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape),
                    model = state.profilePhoto
                )
            }
            InputTextField(
                label = "Your Name*",
                placeholder = "Enter name",
                value = state.name,
                onValueChange = {
                    viewModel.updateForm { copy(name = it) }
                }
            )
            InputDropdownField(
                label = "Select Group*",
                placeholder = "Blood Group",
                value = state.bloodGroup,
                onSelect = {
                    viewModel.updateForm { copy(bloodGroup = it) }
                },
                options = listOf(
                    "A+",
                    "A-",
                    "B+",
                    "B-",
                    "AB+",
                    "AB-",
                    "O+",
                    "O-"
                )
            )

            DatePickerTextField(
                value = localDateToString(state.dob),
                onValueChange = {
                    viewModel.updateForm { copy(dob = stringToLocalDate(it) ?: kotlinx.datetime.LocalDate(
                        2000,
                        1,
                        1
                    )
                    ) }
                }
            )

            InputDropdownField(
                label = "Select Gender*",
                placeholder = "Gender",
                options = listOf("Male", "Female", "Non-binary"),
                value = state.gender,
                onSelect = {
                    viewModel.updateForm { copy(gender = it) }
                }
            )
            InputDropdownField(
                label = "I want to donate blood.",
                placeholder = "Select Option",
                options = listOf("Yes", "No"),
                value = if (state.isDonater) "Yes" else "No",
                onSelect = {
                    viewModel.updateForm { copy(isDonater = it == "Yes") }
                }
            )
            InputTextField(
                label = "Your Email",
                placeholder = "Enter email",
                value = state.email,
                onValueChange = {
                    viewModel.updateForm { copy(email = it) }
                }
            )
            InputDropdownField(
                label = "Select Country",
                placeholder = "Country",
                value = state.country,
                onSelect = {
                    viewModel.updateForm { copy(country = it) }
                },
                options = listOf("India", "UAE", "Sri Lanka")
            )
            InputDropdownField(
                label = "Select City",
                placeholder = "City",
                value = state.city,
                onSelect = {
                    viewModel.updateForm { copy(city = it) }
                },
                options = listOf(
                    "Mumbai",
                    "Delhi",
                    "Bangalore",
                    "Hyderabad",
                    "Ahmedabad",
                    "Chennai",
                )
            )
            InputTextField(
                label = "About yourself",
                placeholder = "Tell us about yourself",
                maxLines = 3,
                value = state.about,
                onValueChange = { viewModel.updateForm { copy(about = it) } }
            )
        }
    }
}