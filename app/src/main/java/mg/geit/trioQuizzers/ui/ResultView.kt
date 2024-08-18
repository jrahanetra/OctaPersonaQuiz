package mg.geit.trioQuizzers.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.OctaPersonaQuizScreen
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.Header
import mg.geit.trioQuizzers.ui.components.MyCardButton
import mg.geit.trioQuizzers.ui.theme.AppTheme
import mg.geit.trioQuizzers.ui.theme.reusableBrush

@Composable
fun ResultScreen(
    navController: NavController
){
    Box {
        Header(
            navController,
            OctaPersonaQuizScreen.Questioning.name
        )
        ContentResult(navController)
    }
}

@Composable
fun ContentResult(
    navController: NavController
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(15.dp)
            )
        // Utilise la couleur de fond du thème
    ){
        val profilePointsMap = mutableMapOf<String, Int>()
        for(i: Int in 0..7) {
            val profil = ListQuestions.getPersonality(i)
            val totalPoint = ListQuestions.totalPointPersonnality(i + 1)
            profilePointsMap[profil] = totalPoint
        }
        /**
         *  Trier le map en ordre décroissant en fonction des valeurs (points)
         */
        val sortedProfilePointsMap = profilePointsMap
            .entries
            .sortedByDescending { it.value }
            .associate { it.key to it.value }

        Log.i("TRY",sortedProfilePointsMap.toString())
        DisplayTop3AndLastProfiles(navController, sortedProfilePointsMap)
    }
}

@Composable
fun DisplayTop3AndLastProfiles(
    navController: NavController,
    sortedProfilPointsMap: Map<String, Int>
){
    val sortedProfiles = sortedProfilPointsMap.keys.toList()
    val profil1 = sortedProfiles[0]
    val profil2 = sortedProfiles[1]
    val profil3 = sortedProfiles[2]
    val profil4 = sortedProfiles[sortedProfilPointsMap.size - 1]
    Column {
        Box(modifier = Modifier.padding(top = 40.dp, bottom = 120.dp)){
            Text(
                text = "Tu es...",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row{
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                PersonnalityCardResult(totalPoint = sortedProfilPointsMap[profil2], personnality = profil2)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .offset(y = (-90).dp), // Ajustez la valeur selon vos besoins ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                PersonnalityCardResult(totalPoint = sortedProfilPointsMap[profil1], personnality = profil1)
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                PersonnalityCardResult(totalPoint = sortedProfilPointsMap[profil3], personnality = profil3)
            }
        }
        Box(modifier = Modifier.padding(top = 40.dp)){
            Text(
                text = "Votre dernier profil est le ...",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PersonnalityCardResult(
                    totalPoint = sortedProfilPointsMap[profil4],
                    personnality = profil4
                )
            }
        }
        Row {
            MyCardButton(
                "FINISH",
                brush = reusableBrush(),
                onSubmitAction = {
                    /**ACTION AFTER ALL */
                    /**
                     * ADD THE RESULT
                     */
                    navController.navigate(OctaPersonaQuizScreen.ShowUser.name)
                }
            )
        }
    }
}
@Composable
fun PersonnalityCardResult(
    totalPoint: Int?,
    personnality: String,
){
    Box(modifier = Modifier.size(90.dp)){
        Image(
            painter = painterResource(R.drawable.ellipse_10),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .size(20.dp)
        )
    }

    Text(
        text = "$totalPoint",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp, bottom = 5.dp),
        textAlign = TextAlign.Center
    )
    Text(
        text = personnality,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        textAlign = TextAlign.Center
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight"
)
@Preview(showBackground = true)
@Composable
fun ShowResult(){
    AppTheme {
        Surface(tonalElevation = 10.dp){
            ResultScreen(
                navController = rememberNavController()
            )
        }
    }
}