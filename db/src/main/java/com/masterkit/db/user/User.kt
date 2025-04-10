package com.masterkit.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var username: String,
                var password: String,
                var phone: String,
                var enable: Boolean = true){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
