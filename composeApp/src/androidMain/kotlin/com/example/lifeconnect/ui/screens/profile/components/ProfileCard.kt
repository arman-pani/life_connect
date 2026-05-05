package com.example.lifeconnect.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.lifeconnect.data.local.entity.UserEntity

val ProfilePlaceholderId = android.R.drawable.ic_menu_gallery
@Composable
fun ProfileCard(
    user: UserEntity,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val CardBackgroundColor = Color(0xFFE96561)
    val IconColor = Color.White
    val TextColor = Color.White
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor)
                .heightIn(min = 175.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp)
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Name
                Text(
                    text = user.name.toString(),
                    color = TextColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Blood Group
                Text(
                    text = "Blood Group: ${user.bloodGroup}",
                    color = TextColor.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp),
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = user.profilePhoto,
                    placeholder = painterResource(id = ProfilePlaceholderId),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                        .clip(CircleShape)
                )
            }

            IconButton(
                onClick = onEditClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(IconColor.copy(alpha = 0.2f))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    tint = IconColor
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileCardPreview() {
//    ProfileCard(
//        profile = mockProfile,
//        onEditClick = { /* Handle edit click */ }
//    )
//}