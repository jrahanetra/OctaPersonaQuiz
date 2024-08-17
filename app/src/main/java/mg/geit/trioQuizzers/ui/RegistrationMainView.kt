package mg.geit.trioQuizzers.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.OctaPersonaQuizScreen
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.ContainerImage
import mg.geit.trioQuizzers.ui.components.LabelAndInput
import mg.geit.trioQuizzers.ui.components.MyCardButton
import mg.geit.trioQuizzers.ui.components.NameOfProject
import mg.geit.trioQuizzers.ui.components.NumberField
import mg.geit.trioQuizzers.ui.theme.AppTheme
import mg.geit.trioQuizzers.ui.theme.reusableBrush

/**
 * MAIN VIEW OF THE REGISTRATION PAGE
 */
@Composable
fun RegistrationMainView(
    listUser: ListUser,
    navController: NavController
){
    ContentView(listUser, navController)
}

/**
 * CONTENT OF THE RegistrationMainView
 */
@SuppressLint("RememberReturnType")
@Composable
fun ContentView(
    listUser: ListUser,
    navController: NavController
){
    val brush = reusableBrush()
    var name by remember { mutableStateOf("") }
    var isNameTrue by remember { mutableStateOf(false) }
    var number by remember { mutableStateOf("") }
    var isNumberTrue by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var isEmailTrue by remember { mutableStateOf(false) }
    var errorVerification by remember { mutableStateOf("") }
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    // Create FocusRequester for each input field
    val nameFocusRequester = remember { FocusRequester() }
    val numberFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController =    LocalSoftwareKeyboardController.current

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Spacer(modifier = Modifier.padding(top = 20.dp))
        NameOfProject(scale = scale)
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        ContainerImage()
        Text(
            text = "My Profil",
            style = MaterialTheme.typography.titleLarge
        )
        LabelAndInput(
            label = "Your firstName",
            modifier = Modifier
                .padding(top = 40.dp, bottom = 20.dp)
                .focusRequester(nameFocusRequester),
            "Rakoto",
            name,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { numberFocusRequester.requestFocus() }
            ),
            onIsTrueChange = { isNameTrue = it},
            onValueChange = { name = it}
        )
        NumberField(
            label = "Your phone number",
            modifier = Modifier
                .padding(bottom = 20.dp)
                .focusRequester(numberFocusRequester),
            placeholder = "0387766797",
            number,
            keyboardActions = KeyboardActions(
                onNext = { emailFocusRequester.requestFocus() }
            ),
            onIsTrueChange = { isNumberTrue = it},
            onValueChange = {number = it}
        )
        LabelAndInput(
            label = "Your email",
            modifier = Modifier
                .padding(bottom = 40.dp)
                .focusRequester(emailFocusRequester),
            placeholder = "rakoto@gmail.com",
            email,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide() // Fermer le clavier après avoir terminé
                    focusManager.clearFocus() // Défocus de tous les champs
                }
            ),
            onIsTrueChange = { isEmailTrue = it},
            onValueChange = {email = it}
        )
        Text(
            text = buildAnnotatedString {
                append("Vous avez déjà un compte? ")
                withStyle(style = SpanStyle(color = colorResource(R.color.blue_sky1))) {
                    append("Login")
                }
            },
            modifier = Modifier
                .padding(bottom = 20.dp)
                .clickable {
                    navController.navigate(OctaPersonaQuizScreen.ShowUser.name)
                }
        )
        Text(
            text = errorVerification,
            color = colorResource(R.color.errorColor),
            modifier = Modifier.padding(bottom = 25.dp)
        )

        MyCardButton(
            nameOfAction = "Start Quiz",
            brush,
            onSubmitAction = {
                if (isEmailTrue && isNumberTrue && isNameTrue){
                    Log.i("Try", "GOOD")
                    //ADD NEW USER
                    errorVerification = listUser.addUser(name, email, number)
                }
                else{
                    errorVerification = when {
                        !isNameTrue -> "Name not correct"
                        !isNumberTrue -> "Number not correct"
                        !isEmailTrue -> "Email not correct"
                        else -> ""
                    }
                }
            }
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight"
)
@Preview(showBackground = true)
@Composable
fun ShowPreviewListAccount(){
    AppTheme {
        Surface (
            tonalElevation = 10.dp
        ){
            ContentView(
                ListUser,
                rememberNavController())
        }
    }
}