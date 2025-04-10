package com.example.masterkit.page.baseui

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.masterkit.util.MyApplication

/**
 * 测试
 */

object ThemeChangeUI {

    @Composable
    fun ChangeDarkThemeView() {
        TextButton(onClick = {
            MyApplication.getInstance().setThemeFlow(false)
        })
        { Text("关闭深色模式") }

        TextButton(onClick = {
            MyApplication.getInstance().setThemeFlow(true)
        }) { Text("打开深色模式") }
    }

}