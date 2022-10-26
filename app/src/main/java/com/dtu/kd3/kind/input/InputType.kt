package com.dtu.kd3.kind.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

sealed class InputType(val label: String, val icon: ImageVector, val keyboardOptions: KeyboardOptions, val visualTransformation: VisualTransformation) {

    object Email : InputType(label = "Email", icon = Icons.Default.Email, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), visualTransformation = VisualTransformation.None)
    object Password : InputType(label = "Kodeord", icon = Icons.Default.Lock, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password), visualTransformation = PasswordVisualTransformation())

}