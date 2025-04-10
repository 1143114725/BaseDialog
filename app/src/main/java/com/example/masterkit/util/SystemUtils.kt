package com.example.masterkit.util

import android.app.Activity
import androidx.annotation.ColorInt
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.core.view.WindowInsetsControllerCompat

object SystemUtils {
    /**
     * 从 Android 11（API 级别 30）开始，推荐使用 `WindowInsetsControllerCompat` 来设置状态栏字体颜色。
     * @param activity Activity
     * @param isLight Boolean true 为深色文本  false 为浅色文本
     */
    fun setStatusBarTextColor(activity: Activity,isLight: Boolean) {
        val windowInsetsController = WindowInsetsControllerCompat(activity.window, activity.window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = isLight
    }

    /**
     * 获取系统是否处于深色模式
     * @return Boolean
     */
    @Composable
    fun getSystemInDarkTheme(): Boolean {
        return isSystemInDarkTheme()
    }

    /**
     * 设置状态栏背景颜色
     * @param activity Activity
     * @param color Int
     */
    fun setStatusBarColor(activity: Activity, @ColorInt color:Int){
        activity.window.statusBarColor = color
    }
}