package mg.geit.trioQuizzers.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.OctaPersonaQuizScreen
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.MyCardButton
import mg.geit.trioQuizzers.ui.theme.AppTheme
import mg.geit.trioQuizzers.ui.theme.reusableBrush

@Composable
fun ShowAboutTheApp(
    navController: NavController
){
    Box {
        HeaderDetails()
        ContentDetailsApp(navController)
    }
}

/**
 * EN-TÊTE DU ShowAboutTheApp
 */
@Composable
fun HeaderDetails(
)
{
    Column(
        modifier = Modifier
            .background(reusableBrush())
            .height(306.dp)
            .fillMaxWidth()// Standard AppBar height
            .padding(top = 20.dp)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                ),
        ){
            Row (
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Icon Info",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 40.dp)
                        .size(40.dp)
                )
                Text(
                    text = "Detail Quiz",
                    modifier = Modifier
                        .padding(
                            vertical = 20.dp,
                            horizontal = 15.dp
                        ),
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(R.drawable.ellipse_10),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 80.dp)
                        .size(60.dp)
                )
            }
        }

        Column (
            modifier = Modifier.padding(
                start = 30.dp,
                top = 20.dp
            )
        ){
            Text(
                text = "Personality Quiz",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "Know who are you",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/**
 * CONTENU DU LISTUSERSCREEN
 */
@Composable
fun ContentDetailsApp(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
            ) // Utilise la couleur de fond du thème
    ){
        Text(
            text = "Brief explanation about this quiz :",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(
                top = 50.dp,
                start = 25.dp
            )
        )

        Column (
            modifier = Modifier.padding(
                top = 20.dp,
                start = 30.dp,
            )
        ){
            ElementDetail(
                title = "64 Questions",
                explanations = "With 5 choices"
            )
            ElementDetail(
                title = "30 Min",
                explanations = "Total duration of quiz"
            )
            ElementDetail(
                title = "Know you 3 first profiles ",
                explanations = "And find who you are"
            )
        }

        Text(
            text = "Please , be honest with yourself because :",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(
                start = 25.dp,
                end = 10.dp
            )
        )

        Column (
            modifier = Modifier
                .padding(
                    top = 15.dp,
                    start = 30.dp,
                )
        ){
            HowYouShouldBeToDo(explanations = "You do this personalities test  to know more about you.")
            HowYouShouldBeToDo(explanations = "You could make some changement with it.")
            HowYouShouldBeToDo(explanations = "You can be more disciplined and happy.")
        }

        Text(
            text = "Click submit if you are sure you want to complete all the quiz",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    start = 30.dp,
                    bottom = 30.dp
                )
        )

        MyCardButton(
            nameOfAction = "BEGIN",
            brush = reusableBrush()
        ){
            /**
             * NAVIGATE COMPOSANT TO ASK QUESTION
             */
            navController.navigate(OctaPersonaQuizScreen.Questioning.name)
        }
    }
}

/**
 * ELEMENT DU DÉTAIL
 */
@Composable
fun ElementDetail(
    title: String,
    explanations: String
){
    Row (
        modifier = Modifier.padding(bottom = 15.dp)
    ){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "",
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp),
            tint = Color.Yellow
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = explanations,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/**
 * ELEMENT DU DÉTAIL
 */
@Composable
fun HowYouShouldBeToDo(
    explanations: String
){
    Row(
        modifier = Modifier.padding(
            end = 30.dp,
            bottom = 5.dp
        )
    ) {
        Icon(
            Icons.Filled.Check,
            contentDescription = "Check mark",
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = explanations,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight"
)
@Preview(showBackground = true)
@Composable
fun ShowPreview1(){
    AppTheme {
        Surface(tonalElevation = 10.dp){
            ShowAboutTheApp(
                navController = rememberNavController()
            )
        }
    }
}