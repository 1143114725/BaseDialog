package com.masterkit.db.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    //获取所有数据
    @Query("SELECT * FROM user")
    fun queryAll(): MutableList<User>

    /**
     * 注册（导入）用户
     * @param user User
     * @return Long
     */
    @Insert
    suspend fun insertUser(user: User): Long
    /**
     * 注销账户
     */
    @Query("UPDATE user SET enable = 'false', enable = 3 WHERE id = 2")
    fun enableUserToPhone()
    /**
     * 修改密码
     */
    /**
     * 登录
     */
    /**
     * 退出登录
     */
    /**
     * 获取用户信息
     */
    /**
     * 修改用户信息
     */

}