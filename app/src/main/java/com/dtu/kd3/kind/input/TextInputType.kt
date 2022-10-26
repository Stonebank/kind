package com.dtu.kd3.kind.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

/**
 * @author: s205409 - Hassan Kassem
 */

sealed class TextInputType(val label: String, val icon: ImageVector, val keyboardOptions: KeyboardOptions, val visualTransformation: VisualTransformation) {

    object Name : TextInputType(label = "Fulde navn", icon = Icons.Default.Person, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), visualTransformation = VisualTransformation.None)
    object Email : TextInputType(label = "Email", icon = Icons.Default.Email, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), visualTransformation = VisualTransformation.None)
    object Password : TextInputType(label = "Kodeord", icon = Icons.Default.Lock, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password), visualTransformation = PasswordVisualTransformation())

}