package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mg.geit.trioQuizzers.ui.theme.reusableBrush

@Composable
fun ButtonStart(
    actionToDo: () -> Unit,
    scale: Float,
    size: Int,
    isForNavigationQuestion: Boolean,
    brush: Brush,
    isNext: Boolean
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = { actionToDo() },
        modifier = Modifier
            .padding(bottom = 55.dp)
            .size(size.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin.Center
            },
        shape = CircleShape,
    ) {
        ContentButton(
            isForNavigationQuestion,
            isNext,
            brush
        )
    }
}

@Composable
fun ContentButton(
    isForNavigationQuestion: Boolean,
    isNext: Boolean,
    brush: Brush
) {
    Box(
        modifier = Modifier
            .background(brush)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (isForNavigationQuestion){
            if (isNext) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Go to Next question"
                )
            }else{
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go to Next question"
                )
            }
        }else{
            Text(
                text = "GO",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}