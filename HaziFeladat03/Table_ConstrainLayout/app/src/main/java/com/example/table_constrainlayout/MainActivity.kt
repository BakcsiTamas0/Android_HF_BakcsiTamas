package com.example.table_constrainlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast

data class Product(val code:Int, val name:String, val price:Double)
class MainActivity : AppCompatActivity() {

    private val products = ArrayList<Product>()
    private val maxProducts = 8
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productTable:TableLayout = findViewById(R.id.productTable)

        val addProductButton:Button = findViewById(R.id.addProductButton)
        val cancelButton:Button = findViewById(R.id.cancelButton)
        val showProducts:Button = findViewById(R.id.showProductsButton)

        val productCode:EditText = findViewById(R.id.productCodeEditText)
        val productName:EditText = findViewById(R.id.productNameEditText)
        val productPrice:EditText = findViewById(R.id.productPriceEditText)

        addProductButton.setOnClickListener{
            val code = productCode.text.toString().toInt()
            val name = productName.text.toString()
            val price = productPrice.text.toString().toDouble()

            if (code!=null && name.isNotEmpty() && price!=null){
                val product = Product(code, name, price)
                products.add(product)
                productCode.text.clear()
                productName.text.clear()
                productPrice.text.clear()
            }
        }

        cancelButton.setOnClickListener{
            productCode.text.clear()
            productName.text.clear()
            productPrice.text.clear()
        }

        showProducts.setOnClickListener{
            if (products.isEmpty()) {
                Toast.makeText(this, "No products to show", Toast.LENGTH_SHORT).show()
            } else {
                productTable.removeAllViews()
                for (product in products) {
                    addProductToTable(product, productTable)
                }
            }
        }
    }
    private fun addProductToTable(product: Product, tableLayout: TableLayout) {
        if (products.size <= maxProducts) {
            val tableRow = TableRow(this)

            val productCodeView = TextView(this)
            productCodeView.text = product.code.toString()
            productCodeView.gravity = Gravity.START
            productCodeView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT)

            val productNameView = TextView(this)
            productNameView.text = product.name
            productNameView.gravity = Gravity.START
            productNameView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT)

            val productPriceView = TextView(this)
            productPriceView.text = product.price.toString()
            productPriceView.gravity = Gravity.START
            productPriceView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT)

            tableRow.addView(productCodeView)
            tableRow.addView(productNameView)
            tableRow.addView(productPriceView)

            val layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            tableRow.layoutParams = layoutParams

            tableLayout.addView(tableRow)
        }
    }
}

