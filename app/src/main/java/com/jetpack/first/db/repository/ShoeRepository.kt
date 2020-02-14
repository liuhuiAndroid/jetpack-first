package com.jetpack.first.db.repository

import com.jetpack.first.db.dao.ShoeDao
import com.jetpack.first.db.data.Shoe

class ShoeRepository private constructor(private val shoeDao: ShoeDao) {

    fun getAllShoes() = shoeDao.getAllShoes()

    fun findAllShoe() = shoeDao.findAllShoe()

    /**
     * 通过品牌查询鞋子
     */
    fun getShoesByBrand(brand:String) = shoeDao.findShoeByBrand(brand)

    /**
     * 插入鞋子的集合
     */
    fun insertShoes(shoes: List<Shoe>) = shoeDao.insertShoes(shoes)

    companion object {
        @Volatile
        private var instance: ShoeRepository? = null

        fun getInstance(shoeDao: ShoeDao): ShoeRepository =
            instance ?: synchronized(this) {
                instance
                    ?: ShoeRepository(shoeDao).also {
                        instance = it
                    }
            }

    }
}