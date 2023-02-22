package com.zeroone.jetpackcompose_instagram.presentation.ui.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.jetpackcompose_instagram.R

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: Int? = null,
    hint : Int? = null,
    trailingIcon: Int? = null,
    trailingOnClick: () -> Unit = { },
    isPasswordVisible: Boolean = false,
    enabled: Boolean = true,
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        enabled = enabled,
        label = {
            if (hint != null)
                Text(
                    text = stringResource(id = hint),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface,
                )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        visualTransformation = if (isPasswordVisible) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {
            if (trailingIcon != null && isFocused) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (placeHolder != null) {
                        Text(
                            text = stringResource(id = placeHolder),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    IconButton(onClick = trailingOnClick) {
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = null
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.onSurface.copy(.25f),
            unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(.25f),
        )
    )

}