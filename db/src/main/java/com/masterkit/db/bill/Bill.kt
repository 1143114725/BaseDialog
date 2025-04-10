package com.masterkit.db.bill

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bill(
    @ColumnInfo(name = "user_id")
    var userId: String,
    var money: String,
    var type: String,
    var time: Long,
    var enable: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "pay_model")
    var payModel: String? = null

    @ColumnInfo(name = "consumption_id")
    var consumptionId: Int? = null

    @ColumnInfo(name = "accessory_path")
    var accessoryPath: String? = null

    @ColumnInfo(name = "ledger_id")
    var ledgerId: Int? = null
    var remark: String? = null
}
