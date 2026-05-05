package com.example.lifeconnect.ui.screens.onboarding.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.onboarding.components.BasicInfoColumn
import com.example.lifeconnect.ui.screens.onboarding.components.PersonalInfoColumn
import com.example.lifeconnect.ui.screens.onboarding.components.UploadPhotoSection
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.ui.auth.ProfileStep
import com.example.lifeconnect.ui.auth.UserViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(
    viewModel: UserViewModel = koinViewModel(),
    onSuccess: () -> Unit = {}
){
    val pageCount = 3
    val pagerState = rememberPagerState (initialPage = 0) { pageCount }
    val scope = rememberCoroutineScope()

    val profileState = viewModel.formState.collectAsStateWithLifecycle().value

    // 1. Derive the current step from the pager state
    val currentStep by remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> ProfileStep.PERSONAL_INFO
                1 -> ProfileStep.BASIC_INFO
                else -> ProfileStep.PHOTO
            }
        }
    }

// 2. Derive the validity based on the step and the form data
    val isCurrentStepValid by remember(profileState) {
        derivedStateOf {
            viewModel.isStepValid(currentStep)
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile Setup",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon =
                    {
                        if (pagerState.currentPage > 0 ) {
                            IconButton(onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth(),
                userScrollEnabled = false
            ) {page ->
                when (page) {
                    0 -> PersonalInfoColumn(viewModel, profileState )
                    1 -> BasicInfoColumn(viewModel, profileState)
                    2 -> UploadPhotoSection(viewModel, profileState)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            CustomFilledButton(
                enabled = isCurrentStepValid,
                onClick = {
                    if (pagerState.currentPage < pageCount - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        // Logic for final step
                        viewModel.saveProfile()
                        onSuccess()
                    }
                },
                label = if (pagerState.currentPage < pageCount - 1) "Next" else "Home",
            )
        }

    }
}

@Preview
@Composable
fun ProfileSetupScreenPreview(){
    ProfileSetupScreen()
}