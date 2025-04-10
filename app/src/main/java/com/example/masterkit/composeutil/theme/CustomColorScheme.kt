package com.example.masterkit.composeutil.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

// 自定义 ColorScheme 包装类
class CustomColorScheme(
    val colorScheme: ColorScheme,
    val appBg: Color // 自定义颜色属性
)