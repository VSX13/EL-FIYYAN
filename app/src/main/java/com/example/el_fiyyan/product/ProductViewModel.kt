package com.example.el_fiyyan.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ProductViewModel(application: Application) : AndroidViewModel(application)
{
    private var repository: ProductRepository = ProductRepository(application)
    private var allProduct: LiveData<List<Product>> = repository.getAllProduct()
    fun insert(product: Product) {
        repository.insert(product)
    }
    fun update(product: Product) {
        repository.update(product)
    }
    fun delete(product: Product) {
        repository.delete(product)
    }
    fun deleteAllProduct() {
        repository.deleteAllProduct()
    }
    fun getAllProduct(): LiveData<List<Product>> {
        return allProduct
    }
}