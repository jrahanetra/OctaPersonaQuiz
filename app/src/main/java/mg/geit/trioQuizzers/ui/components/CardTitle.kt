package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp

@Composable
fun NameOfProject(
    scale : Float
){
    Card(
        modifier = Modifier.padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.secondaryContainer
        ),

        ) {
        Text(
            text = "OctaPersonaQuiz",
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 10.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                },
            style = LocalTextStyle.current.copy(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                textMotion = TextMotion.Animated
            )

        )
    }
}