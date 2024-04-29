package com.example.basedialog.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.example.basedialog.R
import com.example.basedialog.util.DensityUtil
import com.example.basedialog.veiw.dialog.BaseDialogBuilder

/**
 *
 * @author EraJieZhang
 * @data 2024/4/29
 */
class DomeDialog : Dialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, theme: Int) : super(context, theme)
    fun showDialog() {
        Builder(context)
            .setLayout(R.layout.dome_dialog_layout)
            .setGravity(Gravity.BOTTOM)
            .setDialogAnimations(R.style.ActionSheetDialogAnimation)
            .setLayoutParams(
                DensityUtil.getScreenWidth(context),
                DensityUtil.getScreenHeight(context) / 2
            )
            .createDialog()
            .show()
    }

    class Builder(context: Context) : BaseDialogBuilder(context) {
        override fun initView(layout: View) {
        }
    }

}