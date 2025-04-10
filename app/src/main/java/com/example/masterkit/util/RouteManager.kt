package com.example.masterkit.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.masterkit.R

object RouteManager {

    const val BUNDLE_KEY = "Bundle"

    /**
     * 获取Intent
     */
    private fun getIntent(
        cls: Class<*>,
        context: Context,
        bundle: Bundle? = null
    ): Intent {
        val intent = Intent(context, cls)
        // 添加携带参数
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle)
        }
        // 确保activity跳转的时候不会创建多次相同页面
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        return intent
    }

    /**
     * 路由到指定页面
     */
    @JvmOverloads
    fun routePage(
        cls: Class<*>,
        context: Context? = null,
        bundle: Bundle? = null
    ) {
        val curContext = context ?: MyApplication.getInstance()
        val intent = getIntent(cls, curContext, bundle)
        when (curContext) {
            is Activity -> {
                curContext.startActivity(intent)
            }

            else -> {
                // 非Activity Context需要添加FLAG
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                curContext.startActivity(intent)
            }
        }
    }

    /**
     * 目标页面 - 获取对应的Bundle
     * @return 有值返回值，没数据返回null
     */
    fun getBundle(intent: Intent): Bundle? {
        return intent.getBundleExtra(BUNDLE_KEY)
    }


    fun setStartPageTransition(context: Activity) {
        context.overridePendingTransition(
            R.anim.push_left_in,     // 上一个Activity进入动画
            R.anim.push_left_out  // 当前Activity退出动画
        )
    }

    fun setFinishPageTransition(context: Activity) {
        context.overridePendingTransition(
            R.anim.push_right_in,     // 上一个Activity进入动画
            R.anim.push_right_out  // 当前Activity退出动画
        )
    }

    /**
     * 跳转页面，添加参数，并添加返回 回调
     */
    @JvmOverloads
    fun activityResultRegistry(
        context: ComponentActivity,
        key: String = "key",
        cls: Class<*>,
        bundle: Bundle? = null,
        onActivityResult: (result: ActivityResult) -> Unit
    ) {
        val request =
            context.activityResultRegistry.register(key, StartActivityForResult()) { result ->
                onActivityResult(result)
            }
        request.run {
            val resultIntent = getIntent(cls, context, bundle)
            launch(resultIntent)
        }
    }
}