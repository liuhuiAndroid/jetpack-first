package com.jetpack.first.db.datasource

import androidx.paging.DataSource
import com.jetpack.first.db.data.Shoe
import com.jetpack.first.db.repository.ShoeRepository

class CustomPageDataSourceFactory(private val shoeRepository: ShoeRepository): DataSource.Factory<Int, Shoe>() {

    override fun create(): DataSource<Int, Shoe> {
        return CustomPageDataSource(shoeRepository)
    }

}