package com.example.base.permission

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionMediator
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.ExplainReasonCallback
import com.permissionx.guolindev.callback.ForwardToSettingsCallback
import com.permissionx.guolindev.request.PermissionBuilder

/**
 * 权限申请 PermissionX 工具类
 * @property mLogTag String
 * @property permissionMediator PermissionMediator? 初始化PermissionX
 * @property mContext Context?
 * @property showExplainReason Boolean
 *                  是否展示请求权限前提示
 *                  默认为 false
 *                  若为 true 不设置 ExplainReasonCallback 显示默认dialog
 *                  设置 ExplainReasonCallback 时自动为 true
 * @property showForwardToSettings Boolean
 *                  是否展示拒绝权限后提示
 *                  默认为false
 *                  若为true 不设置 ForwardToSettingsCallback 显示默认dialog
 *                  设置 ForwardToSettingsCallback 时自动为 true
 * @property explainReasonCallback ExplainReasonCallback    解释权限弹窗调用
 * @property forwardToSettingsCallback ForwardToSettingsCallback    拒绝权限后弹窗调用
 * @property permissions MutableList<String>    需要申请的权限数组
 *
 * <code>
 *     PermissionXUtil(this)
 *             .setPermissionList(list)
 *             .setExplainRequestReason { scope, permissions ->
 *                 scope.showRequestReasonDialog(
 *                     permissions,
 *                     "申请以下权限",
 *                     "确定",
 *                     "取消"
 *                 )
 *             }
 *             .setForwardToSettings { scope, permissions ->
 *                 scope.showForwardToSettingsDialog(
 *                     permissions,
 *                     "去设置中手动打开权限",
 *                     "确定",
 *                     "取消"
 *                 )
 *             }
 *             .request { allGranted, grantedList, deniedList ->
 *                 ToastUtils.showToast(allGranted.toString())
 *             }
 *
 *     PermissionXUtil(this)
 *             .setPermissionList(list)
 *             .showExplainReason()
 *             .showForwardToSettings()
 *             .request { allGranted, grantedList, deniedList ->
 *                 ToastUtils.showToast(allGranted.toString())
 *             }
 * </code>
 */
class PermissionXUtil {
    private val mLogTag = "PermissionXUtil"

    // 初始化，获取PermissionMediator
    private var permissionMediator: PermissionMediator? = null
    private var mContext: Context? = null

    constructor(fragmentActivity: FragmentActivity) {
        mContext = fragmentActivity
        permissionMediator = PermissionX.init(fragmentActivity)
    }

    constructor(fragment: Fragment) {
        mContext = fragment.context
        permissionMediator = PermissionX.init(fragment)
    }

    // 展示请求权限前提示
    private var showExplainReason = false

    // 展示请求权限被拒绝的提示
    private var showForwardToSettings = false

    // 请求权限前的提示弹窗
    private var explainReasonCallback: ExplainReasonCallback =
        ExplainReasonCallback { scope, permissions ->
            scope.showRequestReasonDialog(
                permissions,
                "申请以下权限",
                "确定",
                "取消"
            )
        }

    // 拒绝权限后的提示回调
    private var forwardToSettingsCallback: ForwardToSettingsCallback =
        ForwardToSettingsCallback { forwardScope, strings ->
            forwardScope.showForwardToSettingsDialog(
                permissions,
                "去设置中手动打开权限",
                "确定",
                "取消"
            )
        }

    // 需要申请的权限数组
    private val permissions: MutableList<String> = mutableListOf()

    /**
     * 设置要申请的权限数据
     * @param list MutableList<String>
     * @return PermissionXUtil
     */
    fun setPermissionList(list: MutableList<String>): PermissionXUtil {
        permissions.clear()
        permissions.addAll(list)
        return this
    }

    /**
     * 展示请求权限前提示
     * @return PermissionXUtil
     */
    fun showExplainReason(): PermissionXUtil {
        showExplainReason = true
        return this
    }

    /**
     * 设置请求权限前的提示
     * @param callback ExplainReasonCallback
     * @return PermissionXUtil
     */
    fun setExplainRequestReason(callback: ExplainReasonCallback): PermissionXUtil {
        showExplainReason = true
        explainReasonCallback = callback
        return this
    }

    /**
     * 展示请求权限被拒绝的提示
     * @return PermissionXUtil
     */
    fun showForwardToSettings(): PermissionXUtil {
        showForwardToSettings = true
        return this
    }

    /**
     * 设置权限被拒绝后的引导提示
     * @param callback ForwardToSettingsCallback
     * @return PermissionXUtil
     */
    fun setForwardToSettings(callback: ForwardToSettingsCallback): PermissionXUtil {
        showForwardToSettings = true
        forwardToSettingsCallback = callback
        return this
    }

    /**
     * 权限请求
     * @param callback RequestCallback
     */
    fun request(callback: PermissionRequestCallback) {
        // 设置需要请求的权限，获取PermissionBuilder
        val permissionBuilder: PermissionBuilder? = permissionMediator?.permissions(permissions)

        if (showExplainReason) {
            // 设置请求权限前的提示Dialog
            permissionBuilder?.explainReasonBeforeRequest()
            permissionBuilder?.onExplainRequestReason { scope, permissions ->
                explainReasonCallback.onExplainReason(scope, permissions)
            }
        }

        if (showForwardToSettings) {
            // 设置请求权限被拒绝的提示Dialog
            permissionBuilder?.onForwardToSettings { scope, permissions ->
                forwardToSettingsCallback.onForwardToSettings(scope, permissions)
            }
        }

        // 权限请求
        permissionBuilder?.request { allGranted, grantedList, deniedList ->
            callback.onResult(allGranted, grantedList, deniedList)
        }

    }
}