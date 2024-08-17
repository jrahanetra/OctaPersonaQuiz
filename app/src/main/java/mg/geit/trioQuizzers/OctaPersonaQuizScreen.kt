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
import mg.geit.trioQuizzers.ui.ListAccountUser
import mg.geit.trioQuizzers.ui.ListUser
import mg.geit.trioQuizzers.ui.RegistrationMainView
import mg.geit.trioQuizzers.ui.components.ShowAboutTheApp

enum class OctaPersonaQuizScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Registration(title = R.string.user_registration),
    ShowUser(title = R.string.show_users),
    AboutTheApp(title = R.string.about_the_app)
}

@Composable
fun OctaPersonaQuizApp(){
    val navController = rememberNavController()
    val listUser = ListUser
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
                    listUser,
                    navController
                )
            }
            composable(route = OctaPersonaQuizScreen.ShowUser.name){
                ListAccountUser(
                    navController
                )
            }
            composable(route = OctaPersonaQuizScreen.AboutTheApp.name){
                ShowAboutTheApp(
                )
            }
        }
    }
}