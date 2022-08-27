package com.zeroone.instagramclone_jetpackcompose.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp,
    val extraSmall : Dp,
    val small : Dp,
    val medium : Dp,
    val large : Dp,
    val extraLarge : Dp,
)

@Composable
fun rememberSpacing() : Spacing {
    val configuration = LocalConfiguration.current

    return Spacing(
        extraSmall = when{
            configuration.densityDpi <=240 -> .5.dp
            configuration.densityDpi <=320 -> 1.dp
            configuration.densityDpi <=480 -> 2.dp
            configuration.densityDpi <=640 -> 4.dp
            else -> 8.dp
        },
        small = when{
            configuration.densityDpi <=240 -> 1.dp
            configuration.densityDpi <=320 -> 2.dp
            configuration.densityDpi <=480 -> 4.dp
            configuration.densityDpi <=640 -> 8.dp
            else -> 16.dp
        },
        medium = when{
            configuration.densityDpi <=240 -> 2.dp
            configuration.densityDpi <=320 -> 4.dp
            configuration.densityDpi <=480 -> 8.dp
            configuration.densityDpi <=640 -> 16.dp
            else -> 32.dp
        },
        large = when{
            configuration.densityDpi <=240 -> 4.dp
            configuration.densityDpi <=320 -> 8.dp
            configuration.densityDpi <=480 -> 16.dp
            configuration.densityDpi <=640 -> 32.dp
            else -> 64.dp
        },
        extraLarge = when{
            configuration.densityDpi <=240 -> 8.dp
            configuration.densityDpi <=320 -> 16.dp
            configuration.densityDpi <=480 -> 32.dp
            configuration.densityDpi <=640 -> 64.dp
            else -> 128.dp
        },
        default = 0.dp
    )
}