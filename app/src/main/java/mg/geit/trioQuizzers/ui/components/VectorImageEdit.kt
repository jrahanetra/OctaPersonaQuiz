package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mg.geit.trioQuizzers.R

@Composable
fun ContainerImage(){
    Card(
        modifier = Modifier
            .size(125.dp),
        border = BorderStroke(2.dp, color = colorResource(R.color.blue_sky1)),
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(R.drawable.editprofil),
            contentDescription = "Picture of Edit Profil",
            modifier = Modifier.fillMaxSize()
        )
    }
}