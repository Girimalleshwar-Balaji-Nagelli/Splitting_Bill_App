package com.example.splittingbillapp.Models

data class Addon(
    val addon_name: String,
    val addonid: Int,
    val addonitem: Addonitem,
    val aname: String,
    val discount: String,
    val id: Int,
    val order_item_id: Int,
    val price: String,
    val qty: Int,
    val total: String,
    val type: String
)