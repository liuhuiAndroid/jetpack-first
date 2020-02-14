package com.jetpack.first.db

import android.content.Context
import com.jetpack.first.db.repository.ShoeRepository

object RepositoryProvider {

    /**
     * 得到鞋的本地仓库
     */
    fun providerShoeRepository(context: Context): ShoeRepository {
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }

}