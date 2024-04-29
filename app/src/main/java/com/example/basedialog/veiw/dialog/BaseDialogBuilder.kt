package com.example.basedialog.veiw.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basedialog.R
import com.example.basedialog.util.DensityUtil

/**
 *
 * @author EraJieZhang
 * @data 2024/3/27
 */
open class BaseDialogBuilder(val context: Context) {
    // 对话框
    lateinit var dialog: Dialog

    // 布局id
    private var resource: Int = 0

    // dialog显示位置 默认下方
    private var curGravity = Gravity.BOTTOM

    // 设置点击其他地方是否消失 默认消失
    private var curCancel: Boolean = true

    // 宽度 默认全屏
    private var curWidth: Int = DensityUtil.getScreenWidth(context)

    // 高度 默认包裹内容
    private var curHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT

    // 布局view
    private lateinit var curLayout: View

    // dialog 布局主题
    private var curTheme: Int = R.style.myTransparentStyle

    // dialog 显示/消失动画   默认无动画
    open var animationStyle: Int = 0

    // 点击返回键的回调
    open var onKeyListener: DialogInterface.OnKeyListener =
        DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss()
                return@OnKeyListener true
            }
            return@OnKeyListener false
        }

    protected fun findView(resId: Int): View {
        return curLayout.findViewById(resId)
    }

    /**
     * 创建dialog
     * @return Builder
     */
    open fun createDialog(): BaseDialogBuilder {
        dialog = Dialog(context, curTheme)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        curLayout = inflater.inflate(resource, null)
        dialog.setContentView(curLayout)
        val adParams = dialog.window?.attributes
        // 设置位置
        adParams?.gravity = curGravity
        // 设置点击其他地方是否关闭
        dialog.setCanceledOnTouchOutside(curCancel)
        // 设置点击返回键的回调
        dialog.setOnKeyListener(onKeyListener)
        // 设置宽度
        adParams?.width = curWidth
        // 设置高度
        adParams?.height = curHeight
        // 设置动画
        if (animationStyle != 0) {
            dialog.window?.setWindowAnimations(animationStyle)
        }
        initView(curLayout)
        return this
    }

    /**
     * 布局逻辑 子类实现
     * @param layout View
     */
    open fun initView(layout: View) {}

    /**
     * 设置布局主题 默认R.style.mystyle
     * 一般不需要更改
     * @param theme Int
     * @return Builder
     */
    open fun setDialogTheme(theme: Int): BaseDialogBuilder {
        curTheme = theme
        return this
    }

    /**
     * 设置dialog的显示/消失动画
     * @param resId Int
     *      从下往上出来，反向消失<p> R.style.ActionSheetDialogAnimation> </p>
     * @return BaseDialogBuilder
     */
    open fun setDialogAnimations(resId: Int): BaseDialogBuilder {
        animationStyle = resId
        return this
    }

    /**
     * 添加点击返回键的回调
     * @param listener OnKeyListener
     * @return BaseDialogBuilder
     */
    open fun addDismissListener(listener: DialogInterface.OnKeyListener): BaseDialogBuilder {
        onKeyListener = listener
        return this
    }

    /**
     * 添加dialog取消回调
     * @param dismissListener Function1<[@kotlin.ParameterName] DialogInterface, Unit>
     * @return Builder
     */
    open fun addDismissListener(dismissListener: (dialog: DialogInterface) -> Unit): BaseDialogBuilder {
        try {
            dialog.setOnDismissListener(DialogInterface.OnDismissListener {
                dismissListener.invoke(it)
            })
        } catch (e: UninitializedPropertyAccessException) {
            throw RuntimeException("请先调用create方法")
        }
        return this
    }

    /**
     * 获取布局回调 <需要先调用create方法>
     *
     * @param layout Function1<[@kotlin.ParameterName] View, Unit>
     * @return Builder
     */
    open fun getLayoutCallBack(layout: (layout: View, dialog: Dialog) -> Unit): BaseDialogBuilder {
        try {
            layout(curLayout, dialog)
        } catch (e: UninitializedPropertyAccessException) {
            throw RuntimeException("请先调用create方法")
        }
        return this
    }

    /**
     * 设置布局宽
     * @param width Int
     * @param height Int
     *      ViewGroup.LayoutParams.MATCH_PARENT
     *      ViewGroup.LayoutParams.WRAP_CONTENT
     *      具体值
     */
    open fun setLayoutParams(width: Int, height: Int): BaseDialogBuilder {
        curWidth = width
        curHeight = height
        return this
    }

    /**
     * 设置布局宽
     * @param width Int
     *      ViewGroup.LayoutParams.MATCH_PARENT
     *      ViewGroup.LayoutParams.WRAP_CONTENT
     *      具体值
     */
    open fun setLayoutWidth(width: Int): BaseDialogBuilder {
        curWidth = width
        return this
    }

    /**
     * 设置布局高
     * @param height Int
     *      ViewGroup.LayoutParams.MATCH_PARENT
     *      ViewGroup.LayoutParams.WRAP_CONTENT
     *      具体值
     */
    open fun setLayoutHeight(height: Int): BaseDialogBuilder {
        curHeight = height
        return this
    }

    /**
     * 设置布局
     * @param layoutId Int
     * @return Builder
     */
    open fun setLayout(layoutId: Int): BaseDialogBuilder {
        resource = layoutId
        return this
    }

    /**
     * 设置显示位置
     * @param gravity Int
     *      Gravity.BOTTOM
     *      Gravity.CENTER
     *      Gravity.TOP
     *      Gravity.START
     *      Gravity.END
     * @return Builder
     */
    open fun setGravity(gravity: Int): BaseDialogBuilder {
        curGravity = gravity
        return this
    }

    /**
     * 设置点击其他地方是否消失 默认消失
     * @param cancel Boolean
     *      true 消失
     *      false 不消失
     * @return Builder
     */
    open fun setCanceledOnTouchOutside(cancel: Boolean): BaseDialogBuilder {
        curCancel = cancel
        return this
    }

    /**
     * 显示dialog
     * @return
     *      BasicsDialog
     */
    open fun show(): Dialog {
        try {
            dialog.show()
        } catch (e: UninitializedPropertyAccessException) {
            throw RuntimeException("请先调用create方法")
        }
        return dialog
    }
}