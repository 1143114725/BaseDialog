package com.example.masterkit.page

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.masterkit.R
import com.example.masterkit.composeutil.theme.Theme
import com.example.masterkit.page.baseui.NavigationUI
import com.example.masterkit.page.baseui.NavigationUI.NavigationItem

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme.MyAppTheme {
                Surface {
                    TestShowView()
                }
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TestShowView() {




        val navigationList:MutableList<NavigationItem> = mutableListOf()
        navigationList.add(NavigationItem("1","title1",R.drawable.icon_app_top_bar_back))
        navigationList.add(NavigationItem("2","title2",R.drawable.icon_app_top_bar_back))
        navigationList.add(NavigationItem("3","title3",R.drawable.icon_app_top_bar_back))
        NavigationUI.Navigation(navigationList){ it ->
            when(it){
                "1" ->{}
                "2" ->{}
                "3" ->{}
            }
        }
    }



}