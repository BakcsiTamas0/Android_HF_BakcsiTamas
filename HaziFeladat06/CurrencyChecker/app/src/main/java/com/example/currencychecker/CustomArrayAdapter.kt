package com.example.currencychecker

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

data class CurrencyItem(
    val imageRes: Int,
    val abbreviation: String,
    val fullName: String,
    val buyingPrice: String,
    val sellingPrice: String,
    val buyPrice: Double,
    val sellPrice: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageRes)
        parcel.writeString(abbreviation)
        parcel.writeString(fullName)
        parcel.writeString(buyingPrice)
        parcel.writeString(sellingPrice)
        parcel.writeDouble(buyPrice)
        parcel.writeDouble(sellPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurrencyItem> {
        override fun createFromParcel(parcel: Parcel): CurrencyItem {
            return CurrencyItem(parcel)
        }

        override fun newArray(size: Int): Array<CurrencyItem?> {
            return arrayOfNulls(size)
        }
    }

    class CurrencyAdapter(
        context: Context,
        private val resource: Int,
        objects: List<CurrencyItem>
    ) :
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

                textBuyingValue.text = currencyItem.buyPrice.toString()
                textSellingValue.text = currencyItem.sellPrice.toString()
            }

            return view
        }
    }
}