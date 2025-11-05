package com.example.lifeconnect.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.R

@Composable
fun BloodGroupGridView(){
    val bloodGroups = listOf(
        R.drawable.a_plus,
        R.drawable.a_minus,
        R.drawable.b_plus,
        R.drawable.b_minus,
        R.drawable.o_minus,
        R.drawable.o_plus,
        R.drawable.ab_plus,
        R.drawable.ab_minus
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text("Blood Group", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp) ,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 4
        ) {
            val modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(10.dp))

            bloodGroups.forEach { group ->
                // Your Box item composable here
                BloodGroupCard(group, modifier)
            }
        }
    }
}

@Composable
fun BloodGroupCard(group: Int, modifier: Modifier){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        ) {
        Image(
            painter = painterResource(id = group),
            contentDescription = "Blood group $group",
            modifier = Modifier.size(50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BloodGroupGridViewPreview(){
    BloodGroupGridView()
}