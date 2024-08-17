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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import mg.geit.trioQuizzers.R


/**
 *
 * THE NUMERICFIELD WITH ICONS
 * @param label : String, the name of one text field
 * @param data : String, the default data of the textField
 * @param onValueChange : Lambda function to listen and update to the change of the data
 */
@Composable
fun NumberField(
    label: String,
    modifier: Modifier,
    placeholder: String,
    data: String,
    keyboardActions: KeyboardActions,
    onIsTrueChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
)
{
    val colorScheme = MaterialTheme.colorScheme
    var color by remember { mutableStateOf(colorScheme.errorContainer) }
    var text by remember { mutableStateOf(TextFieldValue(data)) }
    val numerosRegex = "^[0-9]{10}$".toRegex()

    val errorColor = colorResource(R.color.errorColor) // Couleur d'erreur
    // Vérifier la longueur et la validité du numéros
    LaunchedEffect(text.text) {
        if (numerosRegex.matches(text.text)) {
            color = colorScheme.primary // Couleur de succès
            onIsTrueChange(true)
        } else {
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
            onValueChange = {
                // Allow only numeric values
                val filteredText = it.text.filter { char -> char.isDigit() || char == '.' }
                text = it.copy(text = filteredText)
                onValueChange(filteredText)
            },
            placeholder = {
                Text(
                    text = placeholder
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = keyboardActions,
            shape = MaterialTheme.shapes.large,
        )
    }
}