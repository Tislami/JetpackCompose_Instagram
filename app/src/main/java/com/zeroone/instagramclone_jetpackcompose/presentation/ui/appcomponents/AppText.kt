package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.R

@Composable
fun AppText(text: String,
            modifier: Modifier = Modifier,
            color :Color = MaterialTheme.colors.onBackground,
            fontSize: TextUnit = TextUnit.Unspecified,
            fontStyle: FontStyle? = null,
            fontWeight: FontWeight? = null,
            fontFamily: FontFamily? = null,
            letterSpacing: TextUnit = TextUnit.Unspecified,
            textDecoration: TextDecoration? = null,
            textAlign: TextAlign? = null,
            lineHeight: TextUnit = TextUnit.Unspecified,
            overflow: TextOverflow = TextOverflow.Clip,
            softWrap: Boolean = true,
            maxLines: Int = Int.MAX_VALUE,
            onTextLayout: (TextLayoutResult) -> Unit = {},
            style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        color=color,
        modifier=modifier,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        style = style,
        maxLines = maxLines
    )
}