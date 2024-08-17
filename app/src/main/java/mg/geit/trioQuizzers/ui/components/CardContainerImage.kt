package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mg.geit.trioQuizzers.R

@Composable
fun ContainerImageBackground(){
    Card(
        modifier = Modifier.padding(top = 80.dp, bottom = 100.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.images),
            contentDescription = "Search your personnality Image",
            modifier = Modifier.size(width = 350.dp, height = 327.dp)
        )
    }
}