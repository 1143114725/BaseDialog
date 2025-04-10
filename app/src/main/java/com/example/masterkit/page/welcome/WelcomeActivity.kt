package com.example.masterkit.page.welcome

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.masterkit.R
import com.example.masterkit.composeutil.dp50
import com.example.masterkit.composeutil.theme.Theme
import com.example.masterkit.page.baseui.MasterKitButton
import com.example.masterkit.page.baseui.ThemeChangeUI
import com.example.masterkit.page.baseui.NavigationUI
import com.example.masterkit.page.baseui.NavigationUI.NavigationItem
import com.example.masterkit.page.bill.home.HomePage
import com.example.masterkit.page.dialog.DialogActivity
import com.example.masterkit.page.permission.PermissionsPage
import com.example.masterkit.util.LogUtil
import com.example.masterkit.util.RouteManager
import com.masterkit.db.manager.AppDatabase
import com.masterkit.db.user.OperationUser
import com.masterkit.db.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * @author EraJieZhang
 * @data 2024/4/29
 */
class WelcomeActivity : AppCompatActivity() {

    val mWelcomeViewModel:WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme.MyAppTheme {
                Surface {
                    WelcomeView()
                }
            }
        }
    }

    @Composable
    private fun WelcomeView() {


        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {

                val navigationList:MutableList<NavigationItem> = mutableListOf()
                navigationList.add(NavigationItem("1","首页", R.drawable.icon_app_top_bar_back))
                navigationList.add(NavigationItem("2","title2", R.drawable.icon_app_top_bar_back))
                navigationList.add(NavigationItem("3","title3", R.drawable.icon_app_top_bar_back))
                NavigationUI.Navigation(navigationList,repeatClick = true){ it ->
                    when(it){
                        "1" ->{
                            RouteManager.routePage(HomePage::class.java, this@WelcomeActivity)
                        }
                        "2" ->{
                            LogUtil.v("2")
                        }
                        "3" ->{
                            LogUtil.v("3")
                        }
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                MasterKitButton.AddButton(text = "初始化") {
                    AppDatabase.initDb(this@WelcomeActivity)

                }
                MasterKitButton.AddButton(text = "插入") {
                    lifecycleScope.launch {
                        OperationUser.insertUser( User("zhangshijie","psd","123"))
                    }
                }
                MasterKitButton.AddButton(text = "查询") {

                    lifecycleScope.launch(Dispatchers.IO) {
                        val list = OperationUser.getAllUser()
                        repeat(list.size) {
                            LogUtil.v("${list[it].id} --:--${list[it].username}")
                        }
                    }

                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                AddButton("Show Dialog") {

                    RouteManager.routePage(DialogActivity::class.java, this@WelcomeActivity)
                    RouteManager.setStartPageTransition(this@WelcomeActivity)
                }

                AddButton("权限") {
                    RouteManager.routePage(PermissionsPage::class.java, this@WelcomeActivity)
                }

                AddButton("带返回值") {
                    val key = "AddButton"
                    RouteManager.activityResultRegistry(
                        this@WelcomeActivity,
                        key,
                        PermissionsPage::class.java
                    ) { result ->
                        if (result.resultCode == RESULT_OK) {
                            //获取返回的结果
                            val data = result.data!!.getStringExtra("result")
                            LogUtil.v("key = $key data = $data")
                        }
                    }
                }


            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                AddButton("申请权限") {
                    val key = "Select Media"
                    val request =
                        activityResultRegistry.register(key, PickVisualMedia()) { result ->
                            LogUtil.v("request Permission result = $result")
                        }
                    request.run {
                        launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo))
                    }
                }

                ElevatedButton(
                    onClick = { /* Do something! */ },
//                    colors = ButtonColors(
//                        containerColor = MaterialTheme.colorScheme.primary,
//                        contentColor = MaterialTheme.colorScheme.secondary,
//                        disabledContentColor = MaterialTheme.colorScheme.primaryContainer,
//                        disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer
//                    )
                ) { Text("Elevated Button") }


            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                ThemeChangeUI.ChangeDarkThemeView()
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(onClick = {
                    LogUtil.v("晒晒倒海翻江卡号死的回复看见哈桑给对方空间哈公司贷款v环境下你从不拉开建设的好办法就喀什地方聚宝盆晒晒倒海翻江卡号卡死的回复看见哈桑给对方空间哈公司贷款v环境下你从不拉开建设的好办法就喀什地方聚宝盆")

                }) { Text("ShowLog") }
            }

        }

    }


    @Composable
    private fun AddButton(text: String, callback: () -> Unit) {
        Button(
            modifier = Modifier
                .height(dp50),
            onClick = {
                callback()
            }
        ) {
            Text(text)
        }
    }
}


