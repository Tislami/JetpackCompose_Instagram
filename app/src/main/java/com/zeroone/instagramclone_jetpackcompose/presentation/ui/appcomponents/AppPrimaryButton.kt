package com.zeroone.instagramclone_jetpackcompose.presentation.ui.appcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroone.instagramclone_jetpackcompose.presentation.ui.theme.InstagramClone_JetpackComposeTheme

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(.5f),
        ),
    ) {
        Text(
            text = text,
            color = if (enabled) MaterialTheme.colors.onPrimary
            else MaterialTheme.colors.onPrimary.copy(.5f),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(.5f),
        ),
        border = BorderStroke(1.dp,MaterialTheme.colors.secondaryVariant),
    ) {
        Text(
            text = text,
            color = if (enabled) MaterialTheme.colors.onPrimary
            else MaterialTheme.colors.onPrimary.copy(.5f),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}


@Preview
@Composable
fun PreviewInstagramButton() {
    InstagramClone_JetpackComposeTheme() {
        AppPrimaryButton(text = "Log in.", onClick = { /*TODO*/ })
    }
}
