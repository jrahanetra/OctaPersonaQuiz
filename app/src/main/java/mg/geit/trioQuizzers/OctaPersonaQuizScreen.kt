package mg.geit.trioQuizzers

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.ui.HomeScreen
import mg.geit.trioQuizzers.ui.RegistrationMainView

enum class OctaPersonaQuizScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Registration(title = R.string.user_registration),
}

@Composable
fun OctaPersonaQuizApp(){
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController,
            startDestination = OctaPersonaQuizScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route =  OctaPersonaQuizScreen.Start.name) {
                HomeScreen(
                    navController
                )
            }
            composable(route = OctaPersonaQuizScreen.Registration.name){
                RegistrationMainView(
                    navController
                )
            }
        }
    }
}