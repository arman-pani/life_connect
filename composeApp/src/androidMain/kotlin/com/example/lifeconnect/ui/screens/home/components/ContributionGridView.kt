package com.example.lifeconnect.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

//@Preview(showBackground = true)
@Composable
fun ContributionGridView(){
    val contributions = listOf<ContributionDetails>(
            ContributionDetails("+1K", "Blood Doners", Color.Blue),
            ContributionDetails("+20K", "Blood Donations", Color.Cyan),
            ContributionDetails("20", "NGOs Involved", Color.Green),
            ContributionDetails("+30K", "Lives Saved", Color.Red),
            ContributionDetails("50+", "Clinics Involved", Color.Magenta),
            ContributionDetails("50+", "Clinics Involved", Color.Cyan),
        )



    Column (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text("Our Contributions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp) ,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 3
        ) {
            contributions.forEach{ contribution ->
                val modifier = Modifier
                    .weight(1.2f)
                    .height(90.dp)
                    .background(color = contribution.color.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))

                ContributionCard(contribution, modifier)
            }
        }
    }
}

@Composable
fun ContributionCard(contribution: ContributionDetails, modifier: Modifier){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(contribution.title, style = MaterialTheme.typography.titleLarge, color = contribution.color, fontWeight = FontWeight.Bold)
            Text(contribution.subtitle, style = MaterialTheme.typography.bodySmall, color = contribution.color)
        }
    }
}

data class ContributionDetails(
    val title: String,
    val subtitle: String,
    val color: Color
)