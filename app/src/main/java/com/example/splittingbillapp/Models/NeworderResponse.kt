package com.example.splittingbillapp.Models

data class NeworderResponse(
    val message: List<Message>,
    val response_code: Int,
    val status: Boolean
)