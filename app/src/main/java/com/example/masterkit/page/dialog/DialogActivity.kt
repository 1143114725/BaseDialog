package com.example.masterkit.page.dialog

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.masterkit.composeutil.theme.Theme
import com.example.masterkit.dialog.DomeDialog
import com.example.masterkit.page.baseui.ThemeChangeUI
import com.example.masterkit.util.RouteManager

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme.MyAppTheme {
                Surface {
                    RootCol()
                }
            }
        }
    }

    @Composable
    fun RootCol(){
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            DialogView()
            ChangeTheme()
        }
    }

    @Composable
    fun DialogView() {
        Row(
            Modifier
                .background(Color.Cyan)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                "showDialog",
                modifier = Modifier
                    .background(Color.Red)
                    .width(100.dp)
                    .height(40.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .clickable(
                        interactionSource = null, // 禁用默认的点击反馈
                        indication = null, // 禁用默认的点击反馈
                    ) {
                        showDialog()
                    },
                textAlign = TextAlign.Center
            )

            Text(
                "finish",
                modifier = Modifier
                    .background(Color.Red)
                    .width(100.dp)
                    .height(40.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .clickable(
                        interactionSource = null, // 禁用默认的点击反馈
                        indication = null, // 禁用默认的点击反馈
                    ) {
                        finish()
                        RouteManager.setFinishPageTransition(this@DialogActivity)
                    },
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun ChangeTheme(){
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            ThemeChangeUI.ChangeDarkThemeView()
        }
    }

    private fun showDialog() {
        val dialog = DomeDialog(this)
        dialog.showDialog()
    }

}