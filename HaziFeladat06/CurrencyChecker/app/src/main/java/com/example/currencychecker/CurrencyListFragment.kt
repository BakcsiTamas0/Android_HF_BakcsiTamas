package com.example.currencychecker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.FragmentManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrencyListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrencyListFragment : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_currency_list, container, false)

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

        val listView: ListView = view.findViewById(R.id.curListView)
        val adapter =
            CurrencyItem.CurrencyAdapter(requireContext(), R.layout.currency_list, currencyItemList)

        listView.adapter = adapter

        val fm: FragmentManager = parentFragmentManager

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCurrency = currencyItemList[position]

            val bundle = Bundle()
            bundle.putParcelable("selectedCurrency", selectedCurrency)

            val fragment = CurrencyInfoFragment()
            fragment.arguments = bundle

            // Navigate to CurrencyDetailFragment
            fm.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CurrencyListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CurrencyListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}