package com.example.splittingbillapp.Models

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("status")
    val status: Boolean,

//    @SerializedName("message")
//    val message: OrderResponse
)
