package com.example.masterkit.page.bill.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.masterkit.composeutil.theme.Theme
import com.example.masterkit.page.baseui.NavigationUI
import com.example.masterkit.util.LogUtil

class HomePage : ComponentActivity() {

    private val mHomeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme.MyAppTheme {
                Surface {
                    Column {
                        Spacer(modifier = Modifier.weight(1f))
                        BottomNavigation()
                    }
                }
            }
        }
    }

    @Composable
    fun BottomNavigation() {
        NavigationUI.Navigation(mHomeViewModel.navigationList) {
            when (it) {
                MenuType.Home.value -> {
                    LogUtil.v("点击菜单1")
                }

                MenuType.Statistical.value -> {
                    LogUtil.v("点击菜单2")
                }

                MenuType.Mine.value -> {
                    LogUtil.v("点击菜单3")
                }
            }
        }
    }
}