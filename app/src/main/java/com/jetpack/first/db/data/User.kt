package com.jetpack.first.db.data

import androidx.room.*

/**
 * 用户表
 */
@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "user_account") val account: String, // 账号
    @ColumnInfo(name = "user_pwd") val pwd: String, // 密码
    @ColumnInfo(name = "user_name") val name: String
//    @Embedded val address: Address, // 地址,用于嵌套，里面的字段同样会存储在数据库中。
//    @Ignore val state: Int // 声明某个字段只是临时用，不存储在数据库中。
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}