package com.example.splittingbillapp.API


import com.example.splittingbillapp.Models.MetaData
import com.example.splittingbillapp.Models.NeworderResponse
import com.example.splittingbillapp.Models.Order
import com.example.splittingbillapp.Models.OrderRequest
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("getOrderByTableId")
    fun getOrderByTableId(@Body request: OrderRequest): Call<NeworderResponse>
}