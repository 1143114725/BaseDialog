package com.masterkit.db.manager

open class BaseOperation {

    fun getDataBase():AppDatabase{
        return AppDatabase.getDatabase()
    }
}