package com.example.contextmenu

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val mutableItemList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private var selectedColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.animalListView)
        val list = resources.getStringArray(R.array.animalList).toMutableList()
        mutableItemList.addAll(list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
        registerForContextMenu(listView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort -> {
                mutableItemList.sort()
                adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableItemList)
                listView.adapter = adapter
                true
            }
            R.id.delete -> {
                mutableItemList.clear()
                adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableItemList)
                listView.adapter = adapter
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo
        val selectedItemView = listView.getChildAt(info.position - listView.firstVisiblePosition)
        val textView = selectedItemView.findViewById<TextView>(android.R.id.text1)

        when (item.itemId) {
            R.id.red -> {
                selectedColor = ContextCompat.getColor(this, R.color.redColor)
                textView.setTextColor(selectedColor)
                return true
            }
            R.id.yellow -> {
                selectedColor = ContextCompat.getColor(this, R.color.yellowColor)
                textView.setTextColor(selectedColor)
                return true
            }
            R.id.green -> {
                selectedColor = ContextCompat.getColor(this, R.color.greenColor)
                textView.setTextColor(selectedColor)
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }
}
