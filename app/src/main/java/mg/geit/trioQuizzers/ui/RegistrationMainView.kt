package mg.geit.trioQuizzers.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.ContainerImage
import mg.geit.trioQuizzers.ui.components.MyCardButton
import mg.geit.trioQuizzers.ui.components.NameOfProject
import mg.geit.trioQuizzers.ui.components.NumberField
import mg.geit.trioQuizzers.ui.theme.AppTheme
import mg.geit.trioQuizzers.ui.theme.components.LabelAndInput

@Composable
fun RegistrationMainView(
    navController: NavController
){
    ContentView()
}

@Composable
fun ContentView(){
    val brush = Brush.linearGradient(listOf(
        colorResource(R.color.colorBrush1),
        colorResource(R.color.colorBrush2)
    ))
    var name by remember { mutableStateOf("") }
    var isNameTrue by remember { mutableStateOf(false) }
    var number by remember { mutableStateOf("") }
    var isNumberTrue by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var isEmailTrue by remember { mutableStateOf(false) }
    var errorVerification by remember { mutableStateOf("") }
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
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
            modifier = Modifier.padding(top = 40.dp, bottom = 20.dp),
            "Rakoto",
            name,
            onIsTrueChange = { isNameTrue = it},
            onValueChange = { name = it}
        )
        NumberField(
            label = "Your phone number",
            modifier = Modifier.padding(bottom = 20.dp),
            placeholder = "0387766797",
            number,
            onIsTrueChange = { isNumberTrue = it},
            onValueChange = {number = it}
        )
        LabelAndInput(
            label = "Your email",
            modifier = Modifier.padding(bottom = 40.dp),
            placeholder = "rakoto@gmail.com",
            email,
            onIsTrueChange = { isEmailTrue = it},
            onValueChange = {email = it}
        )
        Text(
            text = errorVerification,
            color = colorResource(R.color.errorColor),
            modifier = Modifier.padding(bottom = 40.dp)
        )
        MyCardButton(
            nameOfAction = "Start Quiz",
            brush,
            onSubmitAction = {
                if (isEmailTrue && isNumberTrue && isNameTrue){
                    //Action to do if correct
                    Log.i("Try", "GOOD")
                    errorVerification = ""
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
fun ShowPreview(){
    AppTheme {
        Surface (
            tonalElevation = 10.dp
        ){
            ContentView()
        }
    }
}