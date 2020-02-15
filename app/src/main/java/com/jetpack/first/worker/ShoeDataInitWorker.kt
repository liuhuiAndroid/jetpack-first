package com.jetpack.first.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.jetpack.first.db.RepositoryProvider
import com.jetpack.first.db.data.Shoe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

/**
 * 创建后台任务
 */
class ShoeDataInitWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Dispatchers.IO : thread name=DefaultDispatcher-worker-1
            Timber.i( "thread name="+Thread.currentThread().name)

            applicationContext.assets.open("shoes.json").use {
                JsonReader(it.reader()).use {
                    val shoeType = object : TypeToken<List<Shoe>>() {}.type
                    val shoeList: List<Shoe> = Gson().fromJson(it, shoeType)

                    val shoeDao = RepositoryProvider.providerShoeRepository(applicationContext)
                    shoeDao.insertShoes(shoeList)
                    // 通知 WorkManager 任务已成功完成
                    Timber.i( "ShoeDataInitWorker success")
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Timber.i( "ShoeDataInitWorker failure")
            // 通知 WorkManager 任务已失败
            Result.failure()
        }
    }

}