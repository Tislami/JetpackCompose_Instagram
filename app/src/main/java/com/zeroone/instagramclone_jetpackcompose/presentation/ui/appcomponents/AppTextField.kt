package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    placeHolder: String? = null,
    trailingIcon: Painter? = null,
    trailingOnClick: () -> Unit = { },
    isPasswordVisible: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier= Modifier
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        enabled = enabled,
        label = {
            Text(
                text = hint,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface,
            )
        },
        textStyle = TextStyle(fontSize = 14.sp),
        visualTransformation = if (isPasswordVisible)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {
            if (trailingIcon != null && isFocused)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (placeHolder != null) {
                        Text(
                            text = placeHolder,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    IconButton(onClick = trailingOnClick) {
                        Icon(
                            painter = trailingIcon,
                            contentDescription = null
                        )
                    }
                }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.onFocusChanged { isFocused = it.isFocused },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.onSurface.copy(.25f),
            unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(.25f),
        )

    )
}