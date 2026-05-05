package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.profile.components.BlogSearchBar
import com.example.lifeconnect.ui.screens.profile.components.DefaultAppBar
import com.example.lifeconnect.ui.screens.profile.components.FeaturedBlogCard
import com.example.lifeconnect.ui.screens.profile.components.VerticalBlogCard
import com.example.lifeconnect.ui.screens.profile.models.BlogPostModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.example.lifeconnect.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    // --- Dummy Data ---
    val featuredPost = BlogPostModel(
        id = 1,
        title = "What to do after donating blood",
        date = "02 Aug 2021",
        imagePainter = painterResource(id = R.drawable.ic_launcher_background)
    )

    val postsList = listOf(
        BlogPostModel(2, "After your donation - Give Blood", "02 Aug 2021", painterResource(id = R.drawable.ic_launcher_background)),
        BlogPostModel(3, "Benefits of blood donation", "02 Aug 2021", painterResource(id = R.drawable.ic_launcher_background)),
        BlogPostModel(
            4,
            "After your donation - Give Blood",
            "02 Aug 2021",
            painterResource(id = R.drawable.ic_launcher_background)
        ),
        BlogPostModel(5, "Tips for a successful blood donation", "03 Aug 2021", painterResource(id = R.drawable.ic_launcher_background)),
    )

    Scaffold(
        topBar = {
            DefaultAppBar("Blogs", onBackClick )
        },
        containerColor = Color(0xFFF9F9F9)
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                BlogSearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    onSearch = { query ->
                        // Handle search logic here
                        println("Searching for: $query")
                    }
                )
            }

            item {
                FeaturedBlogCard(
                    post = featuredPost,
                    onClick = { /* Navigate to featured post details */ }
                )
            }

            items(items = postsList, key = { it.id }) { post ->
                VerticalBlogCard(
                    post = post,
                    onClick = { /* Navigate to regular post details */ }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBlogScreen() {
    MaterialTheme {
        BlogScreen()
    }
}