package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.theme.reusableBrush

/**
 * EN-TÊTE DU LISTACCOUNTUSER
 */
@Composable
fun Header(
    navController: NavController,
    route: String
) {
    Box(
        modifier = Modifier
            .background(reusableBrush())
            .height(96.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .height(80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    navController.navigate(route)
                },
            )
            {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.retourner_dans_l_activit_pr_c_dente),
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 40.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.octapersonaquiz),
                modifier = Modifier.padding(horizontal = 50.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
