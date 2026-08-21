package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TerracottaPrimaryDark,
    onPrimary = TerracottaOnPrimaryDark,
    primaryContainer = TerracottaContainerDark,
    onPrimaryContainer = TerracottaOnContainerDark,
    secondary = TextileSecondaryDark,
    onSecondary = TextileOnSecondaryDark,
    secondaryContainer = TextileSecondaryContainerDark,
    onSecondaryContainer = TextileOnSecondaryContainerDark,
    background = TextileBackgroundDark,
    onBackground = TextileOnSurfaceDark,
    surface = TextileSurfaceDark,
    onSurface = TextileOnSurfaceDark,
    surfaceVariant = TextileSurfaceVariantDark,
    onSurfaceVariant = TextileOnSurfaceVariantDark,
    outline = TextileOutline,
    outlineVariant = TextileOutlineVariant
)

private val LightColorScheme = lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = TerracottaOnPrimary,
    primaryContainer = TerracottaContainer,
    onPrimaryContainer = TerracottaOnContainer,
    secondary = TextileSecondary,
    onSecondary = TextileOnSecondary,
    secondaryContainer = TextileSecondaryContainer,
    onSecondaryContainer = TextileOnSecondaryContainer,
    tertiary = TextileTertiary,
    onTertiary = TextileOnTertiary,
    tertiaryContainer = TextileTertiaryContainer,
    onTertiaryContainer = TextileOnTertiaryContainer,
    background = TextileBackground,
    onBackground = TextileOnBackground,
    surface = TextileSurface,
    onSurface = TextileOnSurface,
    surfaceVariant = TextileSurfaceVariant,
    onSurfaceVariant = TextileOnSurfaceVariant,
    outline = TextileOutline,
    outlineVariant = TextileOutlineVariant
)

@Composable
fun BVocTextileDesignTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Set false to preserve bespoke Khalsa College Textile palette
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
