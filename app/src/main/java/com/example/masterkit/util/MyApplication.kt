package com.example.masterkit.util

import android.app.Application
import com.masterkit.db.manager.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MyApplication : Application() {

    private val mThemeFlow: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)

    companion object {
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication {
            return instance
        }
    }

    init {
        instance = this
    }

    fun getThemeFlow():MutableStateFlow<Boolean>{
        return mThemeFlow
    }

    fun setThemeFlow(theme:Boolean){
        GlobalScope.launch {
            mThemeFlow.emit(theme)
        }
    }


    override fun onCreate() {
        super.onCreate()
        // 初始化数据库
        AppDatabase.initDb(this)
    }
}