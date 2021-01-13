package com.example.el_fiyyan.product

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)
    @Update
    fun update(product: Product)
    @Delete
    fun delete(product: Product)
    @Query("DELETE FROM product_table")
    fun deleteAllProduct()
    @Query("SELECT * FROM product_table ORDER BY stock DESC")
    fun getAllProduct(): LiveData<List<Product>>
}