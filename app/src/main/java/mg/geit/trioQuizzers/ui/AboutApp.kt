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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
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
    navController: NavController,
    name: String
) {
    Box {
        HeaderDetails(navController)
        ContentDetailsApp(navController, name)
    }
}

/**
 * EN-TÊTE DU ShowAboutTheApp
 */
@Composable
fun HeaderDetails(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(reusableBrush())
            .height(306.dp)
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                ),
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(OctaPersonaQuizScreen.Registration.name)
                    },
                )
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.retourner_dans_l_activit_pr_c_dente),
                    )
                }
                Text(
                    text = stringResource(R.string.d_tails_du_quiz),
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

        Column(
            modifier = Modifier.padding(
                start = 30.dp,
                top = 20.dp
            )
        ) {
            Text(
                text = stringResource(R.string.octapersonaquiz),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = stringResource(R.string.d_couvre_ta_personnalit),
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
    navController: NavController,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 200.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
            ) // Utilise la couleur de fond du thème
    ) {
        Text(
            text = stringResource(R.string.brief_explication_propos_du_quiz),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(
                top = 50.dp,
                start = 25.dp
            )
        )

        Column(
            modifier = Modifier.padding(
                top = 20.dp,
                start = 30.dp,
            )
        ) {
            ElementDetail(
                title = stringResource(R.string._64_questions),
                explanations = stringResource(R.string.avec_5_choix)
            )
            ElementDetail(
                title = stringResource(R.string._30_min),
                explanations = stringResource(R.string.dur_e_total_du_quiz)
            )
            ElementDetail(
                title = stringResource(R.string.connais_tes_3_personnalit_s),
                explanations = stringResource(R.string.et_trouves_qui_est_tu)
            )
        }

        Text(
            text = stringResource(R.string.s_il_vous_pla_t_soyez_honn_te_car),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(
                start = 25.dp,
                end = 10.dp
            )
        )

        Column(
            modifier = Modifier
                .padding(
                    top = 15.dp,
                    start = 30.dp,
                )
        ) {
            HowYouShouldBeToDo(explanations = stringResource(R.string.vous_faites_cette_application_pour_vous_conna_tre))
            HowYouShouldBeToDo(explanations = stringResource(R.string.avec_des_fausses_informations_le_r_sultat_sera_non_pr_cis))
            HowYouShouldBeToDo(explanations = stringResource(R.string.tu_peux_tre_plus_disciplin_et_heureux))
        }

        Text(
            text = stringResource(R.string.cliquer_commencer_si_vous_tes_pr_t_r_pondre_toutes_les_questions),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    start = 30.dp,
                    bottom = 30.dp
                )
        )

        MyCardButton(
            nameOfAction = stringResource(R.string.commencer),
            brush = reusableBrush()
        ) {
            /**
             * NAVIGATE COMPOSANT TO ASK QUESTION
             */
            navController.navigate("${OctaPersonaQuizScreen.Questioning.name}/${name}")
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
) {
    Row(
        modifier = Modifier.padding(bottom = 15.dp)
    ) {
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
) {
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
fun ShowPreview1() {
    AppTheme {
        Surface(tonalElevation = 10.dp) {
            ShowAboutTheApp(
                navController = rememberNavController(),
                "Jason"
            )
        }
    }
}