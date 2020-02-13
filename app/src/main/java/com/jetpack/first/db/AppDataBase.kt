package com.jetpack.first.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jetpack.first.db.dao.ShoeDao
import com.jetpack.first.db.dao.UserDao
import com.jetpack.first.db.data.FavouriteShoe
import com.jetpack.first.db.data.Shoe
import com.jetpack.first.db.data.User

/**
 * 数据库文件
 */
@Database(entities = [User::class, Shoe::class, FavouriteShoe::class],version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    // 得到UserDao
    abstract fun userDao(): UserDao
    // 得到ShoeDao
    abstract fun shoeDao(): ShoeDao

    companion object{
        @Volatile
        private var instance:AppDataBase? = null

        fun getInstance(context: Context):AppDataBase{
            return instance?: synchronized(this){
                instance?:buildDataBase(context)
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDataBase(context: Context):AppDataBase{
            return Room
                .databaseBuilder(context,AppDataBase::class.java,"jetPackDemo-database")
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .build()
        }
    }
}