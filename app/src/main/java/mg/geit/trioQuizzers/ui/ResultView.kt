package mg.geit.trioQuizzers.ui

import android.content.res.Configuration
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
import androidx.compose.ui.res.stringResource
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
    navController: NavController,
    name: String,
    contain: String
) {
    Box {
        Header(
            navController,
            OctaPersonaQuizScreen.ShowUser.name
        )
        ContentResult(navController, name, contain)
    }
}

@Composable
fun ContentResult(
    navController: NavController,
    name: String,
    contain: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(15.dp)
            )
    ) {
        val sortedProfilePointsMap: Map<String, Int>
        if (ListUser.selectUserByName(name).result.isEmpty()) {
            val profilePointsMap = mutableMapOf<String, Int>()
            for (i: Int in 0..7) {
                val profil = ListQuestions.getPersonality(i)
                val totalPoint = ListQuestions.totalPointPersonnality(i + 1)
                profilePointsMap[profil] = totalPoint
            }
            /**
             *  Trier le map en ordre décroissant en fonction des valeurs (points)
             */
            sortedProfilePointsMap = profilePointsMap
                .entries
                .sortedByDescending { it.value }
                .associate { it.key to it.value }
        } else sortedProfilePointsMap = ListUser.selectUserByName(name).result
        DisplayTop3AndLastProfiles(sortedProfilePointsMap, name)
        if (contain == "result") {
            Row {
                MyCardButton(
                    stringResource(R.string.finir),
                    brush = reusableBrush(),
                    onSubmitAction = {
                        /**ACTION AFTER ALL */
                        ListUser.selectUserByName(name).result = sortedProfilePointsMap
                        navController.navigate(OctaPersonaQuizScreen.ShowUser.name)
                    }
                )
            }
        } else {
            Row {
                MyCardButton(
                    nameOfAction = stringResource(R.string.refaire),
                    brush = reusableBrush(),
                    onSubmitAction = {
                        /**ACTION AFTER ALL */
                        ListUser.selectUserByName(name).result = mapOf() // LISTE VIDE AU DÉBUT
                        navController.navigate("${OctaPersonaQuizScreen.Questioning.name}/${name}")
                    }
                )
            }
        }
    }
}

@Composable
fun DisplayTop3AndLastProfiles(
    sortedProfilPointsMap: Map<String, Int>,
    name: String
) {
    val sortedProfiles = sortedProfilPointsMap.keys.toList()
    val profil1 = sortedProfiles[0]
    val profil2 = sortedProfiles[1]
    val profil3 = sortedProfiles[2]
    val profil4 = sortedProfiles[sortedProfilPointsMap.size - 1]
    Column {
        Box(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp)
        ) {
            Text(
                text = name.uppercase(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(reusableBrush())
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.padding(top = 10.dp, bottom = 120.dp)) {
            Text(
                text = stringResource(R.string.tu_es),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PersonnalityCardResult(
                    totalPoint = sortedProfilPointsMap[profil2],
                    personnality = profil2
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .offset(y = (-90).dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PersonnalityCardResult(
                    totalPoint = sortedProfilPointsMap[profil1],
                    personnality = profil1
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PersonnalityCardResult(
                    totalPoint = sortedProfilPointsMap[profil3],
                    personnality = profil3
                )
            }
        }
        Box(modifier = Modifier.padding(top = 40.dp)) {
            Text(
                text = stringResource(R.string.votre_dernier_profil_est_le),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PersonnalityCardResult(
                    totalPoint = sortedProfilPointsMap[profil4],
                    personnality = profil4
                )
            }
        }
    }
}

@Composable
fun PersonnalityCardResult(
    totalPoint: Int?,
    personnality: String,
) {
    Box(modifier = Modifier.size(90.dp)) {
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
fun ShowResult() {
    AppTheme {
        Surface(tonalElevation = 10.dp) {
            ResultScreen(
                navController = rememberNavController(),
                "Jason",
                "Result"
            )
        }
    }
}