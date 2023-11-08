package com.example.currencychecker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrencyInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrencyInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_currency_info, container, false)

        // Retrieve the selected currency from the arguments
        val selectedCurrency = arguments?.getParcelable<CurrencyItem>("selectedCurrency")

        if (selectedCurrency != null) {
            val currencyImageView = view.findViewById<ImageView>(R.id.curImg)
            val abbreviationTextView = view.findViewById<TextView>(R.id.curAbbr)
            val fullNameTextView = view.findViewById<TextView>(R.id.curName)
            val buyingPriceTextView = view.findViewById<TextView>(R.id.buyPrc)
            val sellingPriceTextView = view.findViewById<TextView>(R.id.sellPrc)
            val buyLabel = view.findViewById<TextView>(R.id.buyLabel)
            val sellLabel:TextView = view.findViewById<TextView>(R.id.sellLabel)

            currencyImageView.setImageResource(selectedCurrency.imageRes)
            abbreviationTextView.text = selectedCurrency.abbreviation
            fullNameTextView.text = selectedCurrency.fullName
            buyingPriceTextView.text = selectedCurrency.buyingPrice
            sellingPriceTextView.text = selectedCurrency.sellingPrice
            buyLabel.text = "Cumpara:"
            sellLabel.text = "Vinde:"
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CurrencyInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CurrencyInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}