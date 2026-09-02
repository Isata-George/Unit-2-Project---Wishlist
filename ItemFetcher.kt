package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemFetcher(
    private val items: MutableList<ListItem>
) : RecyclerView.Adapter<ItemFetcher.WishlistViewHolder>() {

    class WishlistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.itemName)
        val price: TextView = view.findViewById(R.id.itemPrice)
        val url: TextView = view.findViewById(R.id.itemUrl)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishlistViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wishlist_item, parent, false)

        return WishlistViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: WishlistViewHolder,
        position: Int
    ) {
        val item = items[position]

        holder.name.text = item.name
        holder.price.text = "$${item.price}"
        holder.url.text = item.url
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: ListItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}