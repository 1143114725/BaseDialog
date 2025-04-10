package com.example.masterkit.page.permission

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.masterkit.composeutil.theme.Theme
import com.example.masterkit.page.baseui.TopBarView
import com.example.masterkit.util.LogUtil
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState


/**
 * 权限页面
 */
class PermissionsPage : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundle()
        setContent {
            Theme.MyAppTheme {
                Surface {
                    Column {
                        PermissionView()
                    }
                }
            }
        }
    }

    fun getBundle() {
        val bundle: Bundle? = intent.getBundleExtra("bundle")
        if (bundle == null) {
            LogUtil.d("没有Bundle")
        }
        bundle?.let {
            val value = it.getString("key")
            LogUtil.d("key 的值为 $value")
        }

    }

    @Composable
    private fun PermissionView() {
        TopBarView.AppTopBar("TopBarTitle", {
            LogUtil.v("点击了返回按钮")
        })


        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            PermissionRequest()
            FinishData()
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun PermissionRequest() {
        //当前需要申请的权限
        val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
        when (cameraPermissionState.status) {
            PermissionStatus.Granted -> {//已授权
                Text("Camera permission Granted")
            }

            is PermissionStatus.Denied -> {
                Column {
                    val textToShow =
                        if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                            //如果用户拒绝了该权限但可以显示理由，那么请温和地解释为什么应用程序需要此权限(拒绝权限)
                            "如果用户拒绝了该权限但可以显示理由，那么请温和地解释为什么应用程序需要此权限(拒绝权限)."
                        } else {
                            //如果这是用户第一次登陆此功能，或者用户不想再次被要求获得此权限，请说明该权限是必需的(用户选择拒绝且不再询问)
                            "如果这是用户第一次登陆此功能，或者用户不想再次被要求获得此权限，请说明该权限是必需的(用户选择拒绝且不再询问)"
                        }
                    Text(textToShow)
                    Button(onClick = {
                        //申请权限
                        cameraPermissionState.launchPermissionRequest()
                    }) {
                        Text("申请权限")
                    }
                }
            }
        }
    }

    @Composable
    fun FinishData() {

        Button(onClick = {
            intent.putExtra("result", "Finish Data!")
            setResult(RESULT_OK, intent)
            finish()
        }) {
            Text("返回页面")
        }


    }

}