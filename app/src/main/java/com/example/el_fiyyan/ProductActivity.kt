package com.example.el_fiyyan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.el_fiyyan.product.AddEditProductActivity
import com.example.el_fiyyan.product.Product
import com.example.el_fiyyan.product.ProductAdapter
import com.example.el_fiyyan.product.ProductViewModel
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    companion object {
        const val ADD_PRODUCT_REQUEST = 1
        const val EDIT_PRODUCT_REQUEST = 2
    }
    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        buttonAddProduct.setOnClickListener {
            startActivityForResult(
                Intent(this, AddEditProductActivity::class.java),
                ADD_PRODUCT_REQUEST
            )
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter = ProductAdapter()
        recycler_view.adapter = adapter
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.getAllProduct().observe(this, Observer<List<Product>> {
            adapter.submitList(it)
        })
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                productViewModel.delete(adapter.getProductAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Product Berhasil Dihapus!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)
        adapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {
            override fun onItemClick(pesanan: Product) {
                val intent = Intent(baseContext, AddEditProductActivity::class.java)
                intent.putExtra(AddEditProductActivity.EXTRA_ID, pesanan.id)
                intent.putExtra(AddEditProductActivity.EXTRA_PRODUCT, pesanan.product)
                intent.putExtra(AddEditProductActivity.EXTRA_HARGA, pesanan.harga)
                intent.putExtra(AddEditProductActivity.EXTRA_STOCK, pesanan.stock)
                startActivityForResult(intent, EDIT_PRODUCT_REQUEST)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_product, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_product -> {
                productViewModel.deleteAllProduct()
                Toast.makeText(this, "Semua Product Berhasil Dihapus!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == Activity.RESULT_OK) {
            val newProduct = Product(
                data!!.getStringExtra(AddEditProductActivity.EXTRA_PRODUCT).toString(),
                data.getStringExtra(AddEditProductActivity.EXTRA_HARGA).toString(),
                data.getIntExtra(AddEditProductActivity.EXTRA_STOCK, 1)
            )
            productViewModel.insert(newProduct)
            Toast.makeText(this, "Poduct Berhasil Disimpan!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_PRODUCT_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddEditProductActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan Product Gagal!", Toast.LENGTH_SHORT).show()
            }
            val updateProduct = Product(
                data!!.getStringExtra(AddEditProductActivity.EXTRA_PRODUCT).toString(),
                data.getStringExtra(AddEditProductActivity.EXTRA_HARGA).toString(),
                data.getIntExtra(AddEditProductActivity.EXTRA_STOCK, 1)
            )
            updateProduct.id = data.getIntExtra(AddEditProductActivity.EXTRA_ID, -1)
            productViewModel.update(updateProduct)
        } else {
            Toast.makeText(this, "Product Tidak Tersimpan!", Toast.LENGTH_SHORT).show()
        }
    }
}