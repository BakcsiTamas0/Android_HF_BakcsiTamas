package com.example.currencychecker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

data class CurrencyItem(
    val imageRes: Int,
    val abbreviation: String,
    val fullName: String,
    val buyingPrice: String,
    val sellingPrice: String,
    val buyValue: Double,
    val sellValue: Double)

class CurrencyAdapter(context: Context, private val resource: Int, objects: List<CurrencyItem>) :
    ArrayAdapter<CurrencyItem>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val currencyItem = getItem(position)
        if (currencyItem != null) {
            val imageCurrency = view.findViewById<ImageView>(R.id.cur_img)
            val textAbbreviation = view.findViewById<TextView>(R.id.cur_abbr)
            val textFullName = view.findViewById<TextView>(R.id.cur_full)
            val textBuyingPrice = view.findViewById<TextView>(R.id.buy_prc)
            val textSellingPrice = view.findViewById<TextView>(R.id.sell_prc)
            val textBuyingValue = view.findViewById<TextView>(R.id.buy_val)
            val textSellingValue = view.findViewById<TextView>(R.id.sell_val)

            imageCurrency.setImageResource(currencyItem.imageRes)
            textAbbreviation.text = currencyItem.abbreviation
            textFullName.text = currencyItem.fullName

            textBuyingPrice.text = "Cumpara:"
            textSellingPrice.text = "Vinde:"

            textBuyingValue.text = currencyItem.buyValue.toString()
            textSellingValue.text = currencyItem.sellValue.toString()
        }

        return view
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgList = listOf(
            R.drawable.european,
            R.drawable.usa,
            R.drawable.uk,
            R.drawable.australia,
            R.drawable.canada,
            R.drawable.switzerland,
            R.drawable.denmark,
            R.drawable.hungary
        )

        val abbrList = listOf<String>(
            "EUR",
            "USD",
            "GBP",
            "AUD",
            "CAD",
            "CHF",
            "DKK",
            "HUF"
        )

        val fullNameList = listOf<String>(
            "Euro",
            "Dollar american",
            "Lira sterlina",
            "Dollar australian",
            "Dollar canadian",
            "Franc elvetian",
            "Corona daneza",
            "Forint maghiar"
        )

        val sellName = "Cumpara:"

        val buyName = "Vinde"

        val buyPrice = listOf<Double>(
            4.4100,
            3.7590,
            6.1520,
            2.9600,
            3.0950,
            4.2300,
            0.5850,
            0.0136
        )

        val sellPrice = listOf<Double>(
            4.5500,
            4.1450,
            6.3550,
            3.0600,
            3.2650,
            4.3300,
            0.6150,
            0.0146
        )

        val currencyItemList = ArrayList<CurrencyItem>()

        for (i in 0 until imgList.size) {
            val currencyItem = CurrencyItem(
                imgList[i],
                abbrList[i],
                fullNameList[i],
                buyPrice[i].toString(),
                sellPrice[i].toString(),
                buyPrice[i],
                sellPrice[i]
            )
            currencyItemList.add(currencyItem)
        }

        val listView = findViewById<ListView>(R.id.curListView)
        val adapter = CurrencyAdapter(this, R.layout.currency_list, currencyItemList)
        listView.adapter = adapter
    }
}
