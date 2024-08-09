package mg.geit.trioQuizzers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mg.geit.trioQuizzers.ui.theme.OctaPersonaQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OctaPersonaQuizTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    ContentHomeScreen()
}

@Composable
fun ContentHomeScreen(){
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Find your profile with ",
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            color = colorResource(R.color.blue_sky1),
        )
        Card(
            modifier = Modifier.padding(top = 20.dp),
            colors = CardDefaults.cardColors(colorResource(R.color.blue_sky2)),
        ) {
            Text(
                text = "TrioQuizzers",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(R.color.blue_sky1),
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin.Center
                    },
                style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)

            )
        }
        Card(
            modifier = Modifier.padding(top = 80.dp, bottom = 200.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.images),
                contentDescription = "Search your personnality Image",
                modifier = Modifier.size(width = 350.dp, height = 327.dp)
            )
        }
        Row {

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(R.color.blue_sky1))) {
                        append("Created by ")
                    }
                    append("Trinome")
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OctaPersonaQuizTheme {
        HomeScreen()
    }
}