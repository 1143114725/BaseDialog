package com.masterkit.db.manager

import android.content.Context
import android.os.Environment

object DataBaseManager {

    // 数据库文件地址
    private var dataBasePath:String = ""
    // 数据库文件名称
    private var dataBaseName:String = "MasterKitDB.db"

    /**
     * 获取数据库文件路径
     * @param context Context
     * @return String
     */
    fun getDataBasePath(context: Context):String{
        if (dataBasePath.isEmpty()){
            dataBasePath = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath + "/app_database_path"
        }
        return dataBasePath
    }

    /**
     * 获取数据库文件名称
     * @return String
     */
    fun getDataBaseName():String{
        return dataBaseName
    }

}