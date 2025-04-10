package com.example.masterkit.util

import android.util.Log
import com.example.masterkit.R

object LogUtil {
    /**
     * 是否打印日志
     */
    private var printLog = false
    private val defTag by lazy { MyApplication.getInstance().getString(R.string.app_name) }

    /**
     * 日志最大长度，超过最大长度则分段打印
     */
    private val maxLogSize = 1000

    private fun showLog(level: LogLevel, tag: String, msg: String) {
        when (level) {
            LogLevel.Debug -> {
                Log.d(tag, msg)
            }

            LogLevel.Error -> {
                Log.e(tag, msg)
            }

            LogLevel.Info -> {
                Log.i(tag, msg)
            }

            LogLevel.Verbose -> {
                Log.v(tag, msg)
            }

            LogLevel.Warn -> {
                Log.w(tag, msg)
            }
        }
    }

    @JvmOverloads
    fun d(msg: String,tag: String = defTag) {
        if (printLog){
            // 不打印日志
            return
        }
        showLongLog(LogLevel.Debug, tag, msg)
    }
    @JvmOverloads
    fun e(msg: String,tag: String = defTag) {
        if (printLog){
            // 不打印日志
            return
        }
        showLongLog(LogLevel.Error, tag, msg)
    }
    @JvmOverloads
    fun i(msg: String,tag: String = defTag) {
        if (printLog){
            // 不打印日志
            return
        }
        showLongLog(LogLevel.Info, tag, msg)
    }
    @JvmOverloads
    fun v(msg: String,tag: String = defTag) {
        if (printLog){
            // 不打印日志
            return
        }
        showLongLog(LogLevel.Verbose, tag, msg)
    }
    @JvmOverloads
    fun w(msg: String,tag: String = defTag) {
        if (printLog){
            // 不打印日志
            return
        }
        showLongLog(LogLevel.Warn, tag, msg)
    }

    /**
     * 分段打印日志
     * @param level LogLevel
     * @param tag String
     * @param msg String
     */
    private fun showLongLog(level: LogLevel, tag: String, msg: String){
        if (msg.length <= maxLogSize) {
            showLog(level, tag, msg)
            return
        }
        var showSize = 0
        while (msg.length - showSize > 0){
            val endIndex = msg.length.coerceAtMost(showSize + maxLogSize)
            val showLog = msg.substring(showSize, endIndex)
            showLog(level, tag,  showLog)
            showSize += showLog.length
        }
    }

}

enum class LogLevel {

    /**
     * 调试信息
     */
    Debug,

    /**
     * 错误信息
     */
    Error,

    /**
     * 提示信息
     */
    Info,

    /**
     * 详细信息
     */
    Verbose,

    /**
     * 警告信息
     */
    Warn
}