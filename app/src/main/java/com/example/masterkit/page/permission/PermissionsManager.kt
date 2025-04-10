package com.example.masterkit.page.permission

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

object PermissionsManager {

    @Composable
    @OptIn(ExperimentalPermissionsApi::class)
    fun RequestPermission(permission: String,permissionCallBack: RequestPermissionCallBack) {
        //当前需要申请的权限
        val cameraPermissionState = rememberPermissionState(permission)
        when (cameraPermissionState.status) {
            PermissionStatus.Granted -> {
                //已授权
                permissionCallBack.granted()
            }

            is PermissionStatus.Denied -> {
                if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                    // 拒绝权限
                    permissionCallBack.askAgain()
                } else {
                    // 拒绝且不在询问
                    permissionCallBack.rationale()
                }
            }
        }
    }
}

interface RequestPermissionCallBack{
    /**
     * 已授权
     */
    @Composable
    fun granted()

    /**
     * 拒绝且不在询问
     */
    @Composable
    fun rationale()

    /**
     * 拒绝权限
     */
    @Composable
    fun askAgain()
}