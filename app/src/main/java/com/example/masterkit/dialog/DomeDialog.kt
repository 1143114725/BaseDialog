package com.example.masterkit.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import com.example.masterkit.R
import com.example.masterkit.util.DensityUtil
import com.example.masterkit.veiw.dialog.BaseDialogBuilder

/**
 *
 * @author EraJieZhang
 * @data 2024/4/29
 */
class DomeDialog(context: Context) : Dialog(context) {
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
//            MasterText
        }
    }

}