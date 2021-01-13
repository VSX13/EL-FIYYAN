package com.example.el_fiyyan.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    var product: String,
    var harga: String,
    var stock: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}