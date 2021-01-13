package com.example.el_fiyyan.product

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ProductRepository(application: Application) {
    private var productDao: ProductDao
    private var allProduct: LiveData<List<Product>>
    init {
        val database: ProductDatabase = ProductDatabase.getInstance(
            application.applicationContext
        )!!
        productDao = database.productDao()
        allProduct = productDao.getAllProduct()
    }
    fun insert(product: Product) {
        val insertProductAsyncTask = InsertProductAsyncTask(productDao).execute(product)
    }
    fun update(product: Product) {
        val updateProductAsyncTask = UpdateProductAsyncTask(productDao).execute(product)
    }
    fun delete(product: Product) {
        val deleteProductAsyncTask = DeleteProductAsyncTask(productDao).execute(product)
    }
    fun deleteAllProduct() {
        val deleteAllProductAsyncTask = DeleteAllProductAsyncTask(
            productDao
        ).execute()
    }
    fun getAllProduct(): LiveData<List<Product>> {
        return allProduct
    }
    companion object {
        private class InsertProductAsyncTask(productDao: ProductDao) : AsyncTask<Product, Unit, Unit>() {
            val productDao = productDao
            override fun doInBackground(vararg p0: Product?) {
                productDao.insert(p0[0]!!)
            }
        }
        private class UpdateProductAsyncTask(productDao: ProductDao) : AsyncTask<Product, Unit, Unit>() {
            val productDao = productDao
            override fun doInBackground(vararg p0: Product?) {
                productDao.update(p0[0]!!)
            }
        }
        private class DeleteProductAsyncTask(productDao: ProductDao) : AsyncTask<Product, Unit, Unit>() {
            val productDao = productDao
            override fun doInBackground(vararg p0: Product?) {
                productDao.delete(p0[0]!!)
            }
        }
        private class DeleteAllProductAsyncTask(productDao: ProductDao) : AsyncTask<Unit, Unit, Unit>() {
            val productDao = productDao
            override fun doInBackground(vararg p0: Unit?) {
                productDao.deleteAllProduct()
            }
        }
    }
}