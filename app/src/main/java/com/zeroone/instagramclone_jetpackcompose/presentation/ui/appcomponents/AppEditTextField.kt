package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun AppEditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelResourceId: Int? = null,
) {

    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value,
        modifier = modifier,
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
        onValueChange = onValueChange,
        cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onSearch = { focusManager.clearFocus(true) }
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(MaterialTheme.colors.background),
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                if (value.isEmpty() && labelResourceId != null) AppText(
                    color = MaterialTheme.colors.onSurface,
                    text = stringResource(id = labelResourceId)
                )
                innerTextField()
            }
        }
    )
}