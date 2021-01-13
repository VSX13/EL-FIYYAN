package com.example.el_fiyyan.product

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object {
        private var instance: ProductDatabase? = null
        fun getInstance(context: Context): ProductDatabase? {
            if (instance == null) {
                synchronized(ProductDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java, "product_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }
    class PopulateDbAsyncTask(db: ProductDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val productDao = db?.productDao()
        override fun doInBackground(vararg p0: Unit?) {
            productDao?.insert(Product("Maryam ORI", "Rp15.000,00", 1))
        }
    }
}