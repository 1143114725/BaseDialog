package com.masterkit.db.user

import com.masterkit.db.manager.BaseOperation

object OperationUser: BaseOperation() {

    suspend fun getAllUser():MutableList<User>{
        return getDataBase().userDao().queryAll()
    }

    suspend fun insertUser(user: User){
        getDataBase().userDao().insertUser(user)
    }

}