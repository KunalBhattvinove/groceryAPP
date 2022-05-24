package com.example.grocerryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerryapp.R
import com.example.grocerryapp.model.GroceryItems

class MyAdapter(var list : List<GroceryItems>, val groceryItemsClickInterface : GroceryItemOnClickInterface) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(R.id.TVITName)
        val quantity  = itemView.findViewById<TextView>(R.id.TVQuantity)
        val rate = itemView.findViewById<TextView>(R.id.TVRate)
        val amount = itemView.findViewById<TextView>(R.id.TVAmt)
        val delete = itemView.findViewById<ImageView>(R.id.IVDelete)
    }


    interface GroceryItemOnClickInterface
    {
        fun onItemClick(groceryItems: GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list.get(position).itemName
        holder.quantity.text = list.get(position).itemQuantity.toString()
        holder.rate.text = "Rs"+ list.get(position).itemPrice.toString()
        val itemTotal:Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amount.text = "Rs"+ itemTotal.toString()
        holder.delete.setOnClickListener {
            groceryItemsClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }


}

