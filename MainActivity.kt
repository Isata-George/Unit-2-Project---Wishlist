package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var itemFetcher: ItemFetcher

    private val items = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val wishlist = findViewById<RecyclerView>(R.id.wishlist)

        val nameInput = findViewById<EditText>(R.id.editTextText)
        val priceInput = findViewById<EditText>(R.id.editTextNumberDecimal)
        val urlInput = findViewById<EditText>(R.id.editTextText2)
        val addButton = findViewById<Button>(R.id.button)

        // Set up ItemFetcher
        itemFetcher = ItemFetcher(items)

        wishlist.layoutManager = LinearLayoutManager(this)
        wishlist.adapter = itemFetcher

        // Add button
        addButton.setOnClickListener {

            val name = nameInput.text.toString().trim()
            val priceText = priceInput.text.toString().trim()
            val url = urlInput.text.toString().trim()

            // Make sure fields aren't empty
            if (name.isEmpty() || priceText.isEmpty() || url.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Convert price to Double
            val price = priceText.toDoubleOrNull()

            if (price == null) {

                Toast.makeText(
                    this,
                    "Please enter a valid price",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Create ListItem
            val item = ListItem(
                name = name,
                price = price,
                url = url
            )

            // Add item to RecyclerView
            itemFetcher.addItem(item)



            // Clear input boxes
            nameInput.text.clear()
            priceInput.text.clear()
            urlInput.text.clear()
        }
    }
}