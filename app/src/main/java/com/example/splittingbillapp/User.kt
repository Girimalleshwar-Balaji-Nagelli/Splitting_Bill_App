package com.example.splittingbillapp

import com.example.splittingbillapp.Models.Message

//data class Item(val name: String, val quantity: Int, val price: Double)

data class User(
    val name: String,
    val imageResId: Int,
    val amount: Double,
    val items: List<String>,
    var isExpanded: Boolean = false
)