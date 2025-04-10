package com.example.masterkit.composeutil.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.masterkit.composeutil.ResColor
import com.example.masterkit.util.MyApplication

object Theme {

    // 亮色模式
    val LightColorPalette = CustomColorScheme(
        colorScheme = lightColorScheme(primary = ResColor.Blue,
                onSurface = ResColor.Yellow),
        appBg = Color.White
    )
    // 暗色模式
    val DarkColorPalette = CustomColorScheme(
        colorScheme = darkColorScheme(
            onSurface = ResColor.Red
        ),
        appBg = Color.Black
    )

    // 创建 LocalCustomColors
    val LocalCustomColors = staticCompositionLocalOf<CustomColorScheme> {
        CustomColorScheme(
            colorScheme = lightColorScheme(primary = ResColor.Blue,
                onSurface = ResColor.Yellow),
            appBg = Color.White
        )
    }

    /**
     * 自定义主题
     * @param content [@androidx.compose.runtime.Composable] Function0<Unit>
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyAppTheme(content: @Composable() () -> Unit) {
        val index = MyApplication.getInstance().getThemeFlow().collectAsState()
        val customColors = if (index.value) {
            DarkColorPalette
        } else {
            LightColorPalette
        }

        CompositionLocalProvider(
            LocalCustomColors provides customColors,
            LocalRippleConfiguration provides null, // 禁用涟漪效果
        ){
            MaterialTheme(
                colorScheme = customColors.colorScheme, // 使用 ColorScheme
                content = content
            )
        }
    }


}