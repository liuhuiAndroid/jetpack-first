package com.jetpack.first.viewmodel

import android.content.Context
import com.jetpack.first.db.RepositoryProvider
import com.jetpack.first.db.repository.ShoeRepository
import com.jetpack.first.viewmodel.factory.ShoeModelFactory

/**
 * ViewModel提供者
 */
object CustomViewModelProvider {

    fun providerShoeModel(context: Context): ShoeModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(repository)
    }
}