package mg.geit.trioQuizzers.ui

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.OctaPersonaQuizScreen
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.ButtonStart
import mg.geit.trioQuizzers.ui.components.ContainerImageBackground
import mg.geit.trioQuizzers.ui.components.NameOfProject
import mg.geit.trioQuizzers.ui.theme.AppTheme
import mg.geit.trioQuizzers.ui.theme.reusableBrush

@Composable
fun HomeScreen(
    navController: NavController,
) {
    ContentHomeScreen(
        goToRegistrationActivity = {
            navController.navigate(OctaPersonaQuizScreen.Registration.name)
        }
    )
}

@Composable
fun ContentHomeScreen(
    goToRegistrationActivity: () -> Unit
){
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
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 30.dp),
            color = colorResource(R.color.blue_sky1),
        )
        NameOfProject(scale = scale)
            ContainerImageBackground()
        ButtonStart(
            goToRegistrationActivity,
            scale,
            80,
            false,
            reusableBrush(),
            isNext = false
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = colorResource(R.color.blue_sky1))) {
                    append("Created by ")
                }
                append("Trinome")
            },
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight"
)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Surface(tonalElevation = 10.dp) {
            HomeScreen(
                navController = rememberNavController()
            )
        }
    }
}