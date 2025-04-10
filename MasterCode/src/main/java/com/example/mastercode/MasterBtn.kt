package com.example.mastercode

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView

class MasterTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(
        context,
        attrs
    ){
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView(attrs)
    }


    /**
     * 状态
     */
    var style: TextStyle = TextStyle.Default

    fun setStyleState(style: TextStyle) {
        this.style = style
        setTextViewStyle()
    }

    fun initView(attrs:AttributeSet){
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MasterTextView)

        // 根据属性索引获取TextStyle的值
        val textStyleIndex = typedArray.getInt(R.styleable.MasterTextView_textStyle,0)
        // 根据索引值设置TextStyle
        style = when (textStyleIndex) {
            1 -> TextStyle.Selected
            2 -> TextStyle.Disabled
            else -> TextStyle.Default
        }
        typedArray.recycle()


        gravity = Gravity.CENTER
        setTextViewStyle()
    }



    fun setTextViewStyle() {
        isClickable = true
        when (style) {
            TextStyle.Default -> {
                background = null
                setTextColor(resources.getColor(R.color.blue_409AFF))
            }

            TextStyle.Selected -> {
                setTextColor(resources.getColor(R.color.white))
                background = resources.getDrawable(R.drawable.shape_409aff_all_radius_5_bg)
            }

            TextStyle.Disabled -> {
                isClickable = false
                setTextColor(resources.getColor(R.color.white))
                background = resources.getDrawable(R.drawable.shape_disabled_bg)

            }
        }
    }


}

enum class TextStyle {
    // 默认（未选中）
    Default,

    // 选中
    Selected,

    // 禁用
    Disabled

}