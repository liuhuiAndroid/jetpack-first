package com.jetpack.first.db.data

import androidx.room.*

/**
 * 鞋表
 */
@Entity(tableName = "shoe")
data class Shoe(
    @ColumnInfo(name = "shoe_name") val name: String // 鞋名
    , @ColumnInfo(name = "shoe_description") val description: String// 描述
    , @ColumnInfo(name = "shoe_price") val price: Float // 价格
    , @ColumnInfo(name = "shoe_brand") val brand: String // 品牌
    , @ColumnInfo(name = "shoe_imgUrl") val imageUrl: String // 图片地址
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}