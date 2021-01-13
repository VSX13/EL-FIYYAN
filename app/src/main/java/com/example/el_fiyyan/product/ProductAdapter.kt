package com.example.el_fiyyan.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.el_fiyyan.R
import kotlinx.android.synthetic.main.product_list.view.*

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.product == newItem.product && oldItem.harga == newItem.harga
                        && oldItem.stock == newItem.stock
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return ProductHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val currentProduct: Product = getItem(position)

        holder.textViewProduct.text = currentProduct.product
        holder.textViewStock.text = currentProduct.stock.toString()
        holder.textViewHarga.text = currentProduct.harga
    }

    fun getProductAt(position: Int): Product {
        return getItem(position)
    }

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var textViewProduct: TextView = itemView.text_view_product
        var textViewStock: TextView = itemView.text_view_stock
        var textViewHarga: TextView = itemView.text_view_harga
    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}