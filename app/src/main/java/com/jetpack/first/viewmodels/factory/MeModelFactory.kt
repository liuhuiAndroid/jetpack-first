package com.jetpack.first.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.first.viewmodels.MeModel
import kotlinx.coroutines.Dispatchers

/**
 * AndroidViewModelFactory 实现类，可以创建 ViewModel 和 AndroidViewModel 子类对象
 * NewInstanceFactory 类，只可以创建 ViewModel 子类对象。
 */
class MeModelFactory(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MeModel(application) as T
    }

}
