package com.example.grocerryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerryapp.R
import com.example.grocerryapp.adapter.MyAdapter
import com.example.grocerryapp.database.GroceryDatabase
import com.example.grocerryapp.model.GroceryItems
import com.example.grocerryapp.repository.GroceryRepo
import com.example.grocerryapp.viewmodel.GroceryViewModel
import com.example.grocerryapp.viewmodel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), MyAdapter.GroceryItemOnClickInterface {

    lateinit var itemRey : RecyclerView
    lateinit var addBtn  : FloatingActionButton
    lateinit var list    : List<GroceryItems>
    lateinit var adapter : MyAdapter
    lateinit var groceryViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemRey = findViewById(R.id.RVItems)
        addBtn  = findViewById(R.id.FTB1)
        list    = ArrayList<GroceryItems>()
        adapter = MyAdapter(list,this)
        itemRey.layoutManager = LinearLayoutManager(this)
        itemRey.adapter       = adapter
        val groceryRepo       = GroceryRepo(GroceryDatabase.getDatabaseInstance(this))
        val factory           = ViewModelFactory(groceryRepo)
        groceryViewModel      = ViewModelProvider(this,factory).get(GroceryViewModel::class.java)

        groceryViewModel.getAllItems().observe(this , Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()

        })

        addBtn.setOnClickListener {
            openDialog()
        }


    }

    private fun openDialog() {
        val dialog = android.app.Dialog(this)
        dialog.setContentView(R.layout.add_items)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancel_button)
        val addBtn    = dialog.findViewById<Button>(R.id.add_button)
        val itemEdt   = dialog.findViewById<EditText>(R.id.EditItemName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.EditItemPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.EditItemQuantity)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        addBtn.setOnClickListener {
            val itemName : String = itemEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val itemPrice : String    = itemPriceEdt.text.toString()
            val qty : Int   =  itemQuantity.toInt()
            val price :Int  =  itemPrice.toInt()

            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty())
            {
                val item = GroceryItems(itemName,qty,price)
                groceryViewModel.insert(item)
                Toast.makeText(applicationContext,"Items Inserted",Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else
            {
                Toast.makeText(applicationContext,"Please Enter ALL The Data", Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()
    }

    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        adapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted...." ,Toast.LENGTH_SHORT).show()
    }
}