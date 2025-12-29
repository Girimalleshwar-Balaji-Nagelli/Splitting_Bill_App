package com.example.splittingbillapp.Models

data class Message(
    val item: List<Item>,
    val order: Order,
    val qrCodeUrl: String,
    val void: List<Any?>
)