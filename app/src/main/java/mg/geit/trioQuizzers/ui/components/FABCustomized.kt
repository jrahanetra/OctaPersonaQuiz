package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyCardButton(
    nameOfAction: String,
    brush: Brush,
    onSubmitAction: () -> Unit
){
    ElevatedCard(
        onClick = { onSubmitAction() } ,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .height(56.dp)
            .padding(horizontal = 35.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp)) // Clip pour respecter la forme arrondie du FAB
                .background(brush),
            contentAlignment = Alignment.Center
        ){
            Text(
                nameOfAction,
                fontSize = 25.sp,
            )
        }

    }
}
