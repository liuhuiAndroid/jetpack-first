package com.jetpack.first.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.first.db.repository.ShoeRepository
import com.jetpack.first.viewmodel.ShoeModel

class ShoeModelFactory(private val repository: ShoeRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoeModel(repository) as T
    }
}