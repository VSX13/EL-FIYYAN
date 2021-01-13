package com.example.el_fiyyan

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Penjualan : AppCompatActivity() {
    private var edtnamakon: EditText? = null
    private var edtnamapro: EditText? = null
    private var edtjumlahpro: EditText? = null
    private var edtharga: EditText? = null
    private var edtuangbay: EditText? = null
    private var btnprocess: Button? = null
    private var btndelete: Button? = null
    private var txtnamakon: TextView? = null
    private var txtnamapro: TextView? = null
    private var txtjumlahpro: TextView? = null
    private var txtharga: TextView? = null
    private var txtuangbay: TextView? = null
    private var txttotal: TextView? = null
    private var txtuangkembali: TextView? = null
    private var txtketerangan: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjualan)

        edtnamakon = findViewById<View>(R.id.namakonsumen) as EditText
        edtnamapro = findViewById<View>(R.id.namaproduct) as EditText
        edtjumlahpro = findViewById<View>(R.id.jumlahproduct) as EditText
        edtharga = findViewById<View>(R.id.harga) as EditText
        edtuangbay = findViewById<View>(R.id.uangbayar) as EditText
        btnprocess = findViewById<View>(R.id.tombol1) as Button
        btndelete = findViewById<View>(R.id.tombol2) as Button
        txtnamakon = findViewById<View>(R.id.namakonsumen) as TextView
        txtnamapro = findViewById<View>(R.id.namaproduct) as TextView
        txtjumlahpro = findViewById<View>(R.id.jumlahproduct) as TextView
        txtharga = findViewById<View>(R.id.harga) as TextView
        txtuangbay = findViewById<View>(R.id.uangbayar) as TextView
        txttotal = findViewById<View>(R.id.total) as TextView
        txtuangkembali = findViewById<View>(R.id.uangkembali) as TextView
        txtketerangan = findViewById<View>(R.id.keterangan) as TextView

        btnprocess!!.setOnClickListener {
            val namakonsumen = edtnamakon!!.text.toString().trim { it <= ' ' }
            val namaproduct = edtnamapro!!.text.toString().trim { it <= ' ' }
            val jumlahproduct = edtjumlahpro!!.text.toString().trim { it <= ' ' }
            val harga = edtharga!!.text.toString().trim { it <= ' ' }
            val uangbayar = edtuangbay!!.text.toString().trim { it <= ' ' }
            val jb = jumlahproduct.toDouble()
            val h = harga.toDouble()
            val ub = uangbayar.toDouble()
            val total = jb * h
            txttotal!!.text = "Total : $total"

            val uangkembalian = ub - total
            if (ub < total) {
                txtketerangan!!.text = "Keterangan : uang bayar kurang Rp " + -uangkembalian
                txtuangkembali!!.text = "Uang Kembali : Rp 0"
            } else {
                txtketerangan!!.text = "Keterangan : Tunggu Kembalian"
                txtuangkembali!!.text = "Uang Kembali : $uangkembalian"
            }

        }
        btndelete!!.setOnClickListener {
            txtnamakon!!.text = " "
            txtnamapro!!.text = " "
            txttotal!!.text = " Total : Rp 0"
            txtharga!!.text = " "
            txtuangbay!!.text = " "
            txtuangkembali!!.text = "Uang Kembali : Rp 0"
            txtjumlahpro!!.text = " "
            txtketerangan!!.text = "Keterangan : - "
            Toast.makeText(applicationContext, "Data Berhasil Dihapus!", Toast.LENGTH_LONG).show()

        }

    }

}