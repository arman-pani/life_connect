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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(
    onSuccess: () -> Unit = {}
){
    val pageCount = 3
    val pagerState = rememberPagerState (initialPage = 0) { pageCount }
    val scope = rememberCoroutineScope()

    val setupSections = listOf<@Composable () -> Unit>(
        { PersonalInfoColumn() },
        { BasicInfoColumn() },
        { UploadPhotoSection() }
    )

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
                setupSections[page]()
            }

            Spacer(modifier = Modifier.weight(1f))

            CustomFilledButton(
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pageCount - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onSuccess()
                        }
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