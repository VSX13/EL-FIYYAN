package com.example.el_fiyyan.product

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.el_fiyyan.R
import kotlinx.android.synthetic.main.activity_add_product.*

class AddEditProductActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.el_fiyyan.product.EXTRA_ID"
        const val EXTRA_PRODUCT = "com.example.el_fiyyan.product.EXTRA_PRODUCT"
        const val EXTRA_HARGA = "com.example.el_fiyyan.product.EXTRA_HARGA"
        const val EXTRA_STOCK = "com.example.el_fiyyan.product.EXTRA_STOCK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        number_picker_stock.minValue = 1
        number_picker_stock.maxValue = 1000
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_product_close)
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Product"
            edit_text_product.setText(intent.getStringExtra(EXTRA_PRODUCT))
            edit_text_harga.setText(intent.getStringExtra(EXTRA_HARGA))
            number_picker_stock.value = intent.getIntExtra(EXTRA_STOCK, 1)
        } else {
            title = "Tambah Product"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_product_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.save_product -> {
                saveProduct()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveProduct() {
        if (edit_text_product.text.toString().trim().isBlank() || edit_text_harga.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Product kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_PRODUCT, edit_text_product.text.toString())
            putExtra(EXTRA_HARGA, edit_text_harga.text.toString())
            putExtra(EXTRA_STOCK, number_picker_stock.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}