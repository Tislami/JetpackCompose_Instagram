package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.zeroone.instagramclone_jetpackcompose.R

@Composable
fun AppSearchTextField(
    value: String,
    onValueChange : (String)-> Unit,
    labelText: String,
    textStyle: TextStyle = TextStyle(),
) {
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value,
        textStyle = TextStyle(
            color = MaterialTheme.colors.onSurface
        ),
        singleLine = true,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { focusManager.clearFocus(true) }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colors.secondaryVariant),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth()
                        .weight(1f)

                ) {
                    Spacer(modifier = Modifier.width(4.dp))

                    if (value.isEmpty())
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )

                    Spacer(modifier = Modifier.width(4.dp))
                    if (value.isEmpty()) Text(text = labelText, style = textStyle)


                    innerTextField()
                }
                if (value.isNotEmpty())
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(id = R.string.clear)
                        )
                    }
            }
        }
    )
}