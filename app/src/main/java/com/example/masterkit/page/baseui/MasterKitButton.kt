package com.example.masterkit.page.baseui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.masterkit.composeutil.dp2
import com.example.masterkit.composeutil.dp46
import com.example.masterkit.composeutil.dp48
import com.example.masterkit.composeutil.dp50
import com.example.masterkit.composeutil.theme.Theme.LocalCustomColors

/**
 * 基础按钮
 */
object MasterKitButton {
    @Composable
    fun AddButton(text: String = "",
                          backgroundColor: Color = LocalCustomColors.current.colorScheme.primary,
                          titleColor: Color = Color.Unspecified,
                          titleFontSize: TextUnit = 14.sp,
                          textAlign: TextAlign = TextAlign.Center,
                          callback: () -> Unit = {}) {
        Button(
            modifier = Modifier
//                .background(backgroundColor)
                .padding(0.dp,4.dp)
                .height(dp50),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor // 只修改背景颜色
            ),
            shape = ButtonDefaults.elevatedShape,
            onClick = {
                callback()
            }
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically), // 文本垂直居中
                color = titleColor,
                fontSize = titleFontSize,
                textAlign = textAlign, // 文本对齐方式
                overflow = TextOverflow.Ellipsis, // 超出部分显示为省略号
                maxLines = 1 // 最大行数，超出部分显示省略号
            )
        }
    }
}