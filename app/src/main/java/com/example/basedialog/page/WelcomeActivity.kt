package com.example.basedialog.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.basedialog.R
import com.example.basedialog.dialog.DomeDialog
import kotlinx.android.synthetic.main.welcome_layout.show_dialog

/**
 *
 * @author EraJieZhang
 * @data 2024/4/29
 */
class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_layout)
        show_dialog.setOnClickListener {
            val dialog = DomeDialog(this)
            dialog.showDialog()
        }
    }
}