import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import com.dtu.kd3.kind.input.TextInputType
import com.dtu.kd3.kind.ui.theme.Shapes
import com.dtu.kd3.kind.ui.theme.secondaryColor

/**
 * author: s205409 - Hassan Kassem
 */


/**
 * TextInput is a composable that is used to create a text input field
 *
 * @param inputType is the type of the text input field
 *
 */

@Composable
fun TextInput(inputType: TextInputType, focusRequester: FocusRequester? = null, keyboardActions: KeyboardActions) {
    var value by remember { mutableStateOf("") }
    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, null) },
        label = { Text(inputType.label) },
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = secondaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardActions = keyboardActions
    )
}

@Composable
fun TextInput(label: String, inputType: TextInputType, focusRequester: FocusRequester? = null, keyboardActions: KeyboardActions) {
    var value by remember { mutableStateOf("") }
    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, null) },
        label = { Text(label) },
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = secondaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardActions = keyboardActions
    )

}