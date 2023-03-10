package com.zeroone.jetpackcompose_instagram.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BlueAccent,
    onPrimary = White,
    secondary = Black,
    secondaryVariant = DarkGrey,
    onSecondary = White,
    background = Black,
    onBackground = White,
    surface = Black20,
    onSurface = White60
)

private val LightColorPalette = lightColors(
    primary = BlueAccent,
    onPrimary = White,
    secondary = White,
    secondaryVariant = WhiteGrey,
    onSecondary = Black,
    background = White,
    onBackground = Black,
    surface = White20,
    onSurface = Black60,
)

@Composable
fun InstagramClone_JetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}