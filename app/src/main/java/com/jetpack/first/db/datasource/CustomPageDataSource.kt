package com.jetpack.first.db.datasource

import androidx.paging.PageKeyedDataSource
import com.jetpack.first.common.BaseConstant
import com.jetpack.first.db.data.Shoe
import com.jetpack.first.db.repository.ShoeRepository
import timber.log.Timber

class CustomPageDataSource(private val shoeRepository: ShoeRepository) : PageKeyedDataSource<Int, Shoe>() {

    // 第一次加载的时候调用
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Shoe>) {
        val startIndex = 0L
        val endIndex: Long = 0L + params.requestedLoadSize
        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, null, 2)
    }

    // 每次分页加载的时候调用
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        Timber.i("startPage:${params.key},size:${params.requestedLoadSize}")

        val startPage = params.key
        val startIndex = ((startPage - 1) * BaseConstant.SINGLE_PAGE_SIZE).toLong() + 1
        val endIndex = startIndex + params.requestedLoadSize - 1
        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        Timber.i("endPage:${params.key},size:${params.requestedLoadSize}")

        val endPage = params.key
        val endIndex = ((endPage - 1) * BaseConstant.SINGLE_PAGE_SIZE).toLong() + 1
        var startIndex = endIndex - params.requestedLoadSize
        startIndex = if (startIndex < 0) 0L else startIndex
        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, params.key + 1)
    }
}