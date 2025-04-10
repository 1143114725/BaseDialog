package com.masterkit.db.manager

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masterkit.db.user.UserDao
import com.masterkit.db.user.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    // 提供一个伴生对象，用于创建数据库实例
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context? = null): AppDatabase {

            if(context == null) return INSTANCE ?: throw NullPointerException("先调用数据库初始化方法")


            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DataBaseManager.getDataBaseName()
                ).build()
                INSTANCE = instance
                instance
            }
        }

        // 初始化数据库，在Application中调用
        fun initDb(context: Context){
            if(INSTANCE != null) {
                Log.d("AppDatabase","数据库已经初始化")
                return
            }
            getDatabase(context)
        }
    }



}