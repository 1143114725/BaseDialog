package com.example.masterkit.page.bill.home

import androidx.lifecycle.ViewModel
import com.example.masterkit.R
import com.example.masterkit.page.baseui.NavigationUI.NavigationItem

class HomeViewModel : ViewModel() {

    val navigationList: MutableList<NavigationItem> = mutableListOf(
        NavigationItem(MenuType.Home.value, "写字", R.mipmap.icon_home),
        NavigationItem(MenuType.Statistical.value, "统计", R.mipmap.icon_statistical),
        NavigationItem(MenuType.Mine.value, "会员", R.mipmap.icon_vip)
    )
}