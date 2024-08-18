package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.theme.reusableBrush

@Composable
fun PossibleResponse(
    stringNumber: String,
    response: String,
    onClick: () -> Unit,
    isSelected: Boolean
){
    val brush = if (isSelected) {
        reusableBrush()
    } else {
        Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.secondary,
                Color.White
            )
        )
    }

    val textColor = if (isSelected) {
        colorResource(R.color.colorBrush1)
    } else {
        MaterialTheme.colorScheme.secondary
    }
    OutlinedCard(
        onClick = {
            onClick()
        },
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .width(320.dp)
            .height(50.dp)
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 8.dp, end = 10.dp)
                    .size(40.dp)
            ) {
                Text(
                    text = stringNumber,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(brush)
                        .fillMaxSize()
                        .padding(top = 3.dp),
                    color = Color.Black
                )
            }
            Text(
                text = response,
                style = MaterialTheme.typography.titleMedium,
                color = textColor,
            )
        }
    }
}