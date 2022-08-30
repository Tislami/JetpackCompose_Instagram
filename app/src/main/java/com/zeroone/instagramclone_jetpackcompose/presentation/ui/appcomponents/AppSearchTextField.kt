package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun AppSearchTextField(
    value: String,
    onValueChange : (String)-> Unit,
    labelText: String,
    textStyle: TextStyle = TextStyle()
) {
    BasicTextField(
        value = value,
        onValueChange =  onValueChange,
        cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colors.secondaryVariant),
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(imageVector = Icons.Default.Search, contentDescription = "")

                Spacer(modifier = Modifier.width(4.dp))
                if (value.isEmpty()) Text(text = labelText, style = textStyle)
                innerTextField()
            }
        }
    )
}