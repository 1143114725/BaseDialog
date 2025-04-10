package com.example.masterkit.page.baseui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.masterkit.composeutil.dp30
import com.example.masterkit.composeutil.dp50
import com.example.masterkit.composeutil.dp6
import com.example.masterkit.composeutil.theme.Theme.LocalCustomColors

/**
 * 导航菜单
 *
 * val navigationList:MutableList<NavigationItem> = mutableListOf()
 *                 navigationList.add(NavigationItem("1","title1", R.drawable.icon_app_top_bar_back))
 *                 navigationList.add(NavigationItem("2","title2", R.drawable.icon_app_top_bar_back))
 *                 navigationList.add(NavigationItem("3","title3", R.drawable.icon_app_top_bar_back))
 *                 NavigationUI.Navigation(navigationList){ it ->
 *                     when(it){
 *                         "1" ->{
 *                             LogUtil.v("1")
 *                         }
 *                         "2" ->{
 *                             LogUtil.v("2")
 *                         }
 *                         "3" ->{
 *                             LogUtil.v("3")
 *                         }
 *                     }
 *                 }
 *
 */
object NavigationUI {


    data class NavigationItem(
        val id: String,
        val title: String,
        val icon: Int
    )

    @Composable
    fun Navigation(
        navigationList: MutableList<NavigationItem>,
        defSelectId: String = navigationList[0].id,
        repeatClick:Boolean = false,
        clickItem: (id: String) -> Unit
    ) {

        var selectedId by remember { mutableStateOf(defSelectId) }

        Row(modifier = Modifier.fillMaxWidth()) {
            navigationList.forEach { item: NavigationItem ->
                Column(
                    modifier = Modifier
                        .height(dp50)
                        .weight(1f)
                        .clickable {
                            if (!repeatClick && selectedId == item.id) return@clickable
                            selectedId = item.id
                            clickItem(item.id)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) { NavigationContent(item, selectedId) }
            }
        }
    }


    @Composable
    fun NavigationContent(item: NavigationItem, selectedId: String) {

        val color = if (selectedId == item.id) {
            LocalCustomColors.current.colorScheme.primary
        } else {
            Color.Unspecified
        }

        if (item.icon != 0){
            Icon(
                painter = painterResource(item.icon),
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(dp30)
                    .padding(dp6)
            )
        }

        Text(
            text = item.title,
            color = color,
            fontSize = 14.sp
        )
    }
}