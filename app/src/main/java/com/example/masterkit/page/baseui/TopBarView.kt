package com.example.masterkit.page.baseui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.masterkit.R
import com.example.masterkit.composeutil.theme.Theme.LocalCustomColors
import com.example.masterkit.composeutil.dp16
import com.example.masterkit.composeutil.dp50

object TopBarView {

    /**
     * App顶部栏
     * @param title String 标题文字
     * @param onLeftClick Function0<Unit> 左侧按钮点击事件回调函数
     * @param trailingView [@androidx.compose.runtime.Composable] Function0<Unit>? 自定义右侧视图，可为空
     * @param backgroundColor Color 背景颜色，默认为当前主题的appBg
     * @param showLeftIcon Boolean 是否显示左侧返回按钮，默认为true
     * @param showTitle Boolean 是否显示标题，默认为true
     * @param titleFontSize TextUnit 字体大小，默认为18sp
     * @param titleColor Color 字体颜色，默认为Color.Unspecified（跟随主题）
     * @param textAlign TextAlign 文本对齐方式，默认为TextAlign.Start
     */
    @Composable
    fun AppTopBar(
        title: String,
        onLeftClick: () -> Unit = {},
        trailingView: (@Composable () -> Unit)? = null,
        backgroundColor: Color = LocalCustomColors.current.appBg,
        showLeftIcon: Boolean = true,
        showTitle: Boolean = true,
        titleFontSize: TextUnit = 18.sp,
        titleColor: Color = Color.Unspecified,
        textAlign: TextAlign = TextAlign.Start,
    ) {
        Row(
            modifier = Modifier
                .height(dp50)
                .fillMaxWidth()
                .background(backgroundColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧返回按钮
            if (showLeftIcon) {
                Box(
                    modifier = Modifier
                        .size(dp50)
                        .clickable { onLeftClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_app_top_bar_back),
                        contentDescription = null,
                        modifier = Modifier
                            .size(dp50)
                            .padding(dp16)
                    )
                }
            }
            // 标题
            if (showTitle) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .wrapContentHeight(Alignment.CenterVertically), // 文本垂直居中
                    color = titleColor,
                    fontSize = titleFontSize,
                    textAlign = textAlign, // 文本对齐方式
                    overflow = TextOverflow.Ellipsis, // 超出部分显示为省略号
                    maxLines = 1 // 最大行数，超出部分显示省略号
                )
            }

            // 右侧菜单 动态展示
            if (trailingView != null) {
                trailingView()
            }
        }
    }


}