package mg.geit.trioQuizzers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import mg.geit.trioQuizzers.R

@Composable
fun LabelAndInput(
    label: String,
    modifier: Modifier,
    placeholder: String,
    value: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onIsTrueChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
){
    val colorScheme = MaterialTheme.colorScheme
    var color by remember { mutableStateOf(colorScheme.errorContainer) }
    var text by remember { mutableStateOf(TextFieldValue(value)) }

    val errorColor = colorResource(R.color.errorColor) // Couleur d'erreur
    // Regex pour valider l'email
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    val nameRegex = "^[A-Za-z ]{6,}$".toRegex()

    // Vérifier la longueur et la validité de l'email
    LaunchedEffect(text.text) {
        if ((label == "Your email" && emailRegex.matches(text.text)) ||
            (label == "Your firstName" && nameRegex.matches(text.text))) {
            color = colorScheme.primary // Couleur de succès
            onIsTrueChange(true)
        }
        else{
            color = errorColor
            onIsTrueChange(false)
        }
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = buildAnnotatedString {
                append(label)
                withStyle(style = SpanStyle(color)) {
                    append(" *")
                }
            },
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .clip(shape = MaterialTheme.shapes.large)
        )
        OutlinedTextField(
            value = text,
            textStyle = LocalTextStyle.current,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onValueChange = {
                text = it
                onValueChange(it.text)
            },
            placeholder = {
                Text(
                    text = placeholder,
                )
            },
            shape = MaterialTheme.shapes.large,
        )
    }

}