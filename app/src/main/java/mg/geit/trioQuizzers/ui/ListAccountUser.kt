package mg.geit.trioQuizzers.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.OctaPersonaQuizScreen
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.Header
import mg.geit.trioQuizzers.ui.theme.AppTheme

@Composable
fun ListAccountUser(
    navController: NavController
) {
    Box {
        Header(
            navController,
            OctaPersonaQuizScreen.Registration.name
        )
        ContentListUser(navController)
    }
}

/**
 * CONTENU DU LISTUSERSCREEN
 */
@Composable
fun ContentListUser(
    navController: NavController
) {
    val listUser = ListUser.getListUser().toMutableList()
    Row(
        modifier = Modifier
            .padding(vertical = 70.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)) // Utilise la couleur de fond du thème

    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            items(listUser) { user ->
                run {
                    ShowAuser(
                        user,
                        navController
                    )
                }
            }
        }
    }
}

/**
 * CARD CONTENANT UN USER
 */
@Composable
fun ShowAuser(
    user: User,
    navController: NavController
) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .height(76.dp)
            .width(500.dp)
            .padding(8.dp),
        onClick = {
            navController.navigate("${OctaPersonaQuizScreen.Result.name}/${user.name}")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ), // Padding pour ajouter de l'espace horizontal
            verticalAlignment = Alignment.CenterVertically, // Centrer verticalement les éléments
            horizontalArrangement = Arrangement.Center // Écarter les éléments
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "User vector Icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleLarge, // Choisissez le style adapté
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)// Occupe tout l'espace disponible à gauche
            )
            IconButton(
                onClick = {
                    ListUser.removeUser(user.idUser)
                    navController.navigate(OctaPersonaQuizScreen.Registration.name)
                },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete User",
                    tint = colorResource(R.color.errorColor)
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight"
)
@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    AppTheme {
        Surface(tonalElevation = 10.dp) {
            ListAccountUser(
                navController = rememberNavController()
            )
        }
    }
}