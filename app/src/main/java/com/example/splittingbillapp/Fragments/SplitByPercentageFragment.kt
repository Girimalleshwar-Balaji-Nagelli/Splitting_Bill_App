package com.example.splittingbillapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splittingbillapp.API.APIController.apiService
import com.example.splittingbillapp.Adapters.RecyclerViewAdapter_bill_summary
import com.example.splittingbillapp.Adapters.RecyclerViewAdapter_percentage
import com.example.splittingbillapp.Adapters.RecyclerViewAdapter_split_by_evenly
import com.example.splittingbillapp.Models.Message
import com.example.splittingbillapp.Models.NeworderResponse
import com.example.splittingbillapp.Models.OrderRequest
import com.example.splittingbillapp.anotherItem
import com.example.splittingbillapp.R
import retrofit2.Call


class SplitByPercentageFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: RecyclerViewAdapter_percentage
    private lateinit var recyclerView_bill : RecyclerView
    private lateinit var recyclerviewadapter_BillSummary: RecyclerViewAdapter_bill_summary
    private lateinit var userList: List<Message>
    private lateinit var textView : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_split_by_percentage, container, false)

        var totalAmount = 0.0

        recyclerView = view.findViewById(R.id.recviewforsplitbypercentage)
        textView = view.findViewById(R.id.total_amount)
        userList = emptyList()

        adapter = RecyclerViewAdapter_percentage(userList) {
//            validatePercentage()
        }

//        adapter = RecyclerViewAdapter_percentage(itemList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        recyclerView_bill = view.findViewById(R.id.recview_bill)
        recyclerviewadapter_BillSummary = RecyclerViewAdapter_bill_summary(userList)
        recyclerView_bill.layoutManager = LinearLayoutManager(requireContext())
        recyclerView_bill.adapter = recyclerviewadapter_BillSummary

        val request = OrderRequest(
            apiKey = "3444CED48713B3DA2EC075CDDE7D562F",
            tableId = 86
        )


        val call = apiService.getOrderByTableId(request)
        call.enqueue(object : retrofit2.Callback<NeworderResponse> {
            override fun onResponse(
                call: Call<NeworderResponse>,
                response: retrofit2.Response<NeworderResponse>
            ) {
                if (response.isSuccessful) {
                    val orderResponse = response.body()
                    Log.d("API", "Success: ${response}")
                    Log.d("API", "Success: ${orderResponse}")
                    if (orderResponse != null && orderResponse.status) {
//                        Log.d("API", "Success: ${orderResponse.message}")
                        userList = orderResponse.message

                        adapter.updateData(userList)
                        recyclerviewadapter_BillSummary.updateData(userList)

                        for ((index, user) in userList.withIndex())
                        {
                            totalAmount += user.order.total.toDouble()
                        }
                        textView.text = " £%.2f".format(totalAmount)

                    } else {
                        Log.e("API", "Failure: ${orderResponse?.message}")
                    }
                } else {
                    Log.e("API", "Server Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NeworderResponse>, t: Throwable) {
                Log.e("API", "Network Error", t)
            }
        })

        return view
    }

//    private fun validatePercentage() {
////        val total = userList.sumOf { it.percentage }
//
//        val total = 100
//
//        if (total == 100) {
//            Toast.makeText(requireContext(),"1000 %",Toast.LENGTH_LONG).show()
////            binding.statusText.text = "✅ Total is 100%"
////            binding.statusText.setTextColor(Color.GREEN)
//        } else {
////            binding.statusText.text = "❌ Total = $total%. It must be 100%."
////            binding.statusText.setTextColor(Color.RED)
//            Toast.makeText(requireContext(),"0000 %",Toast.LENGTH_LONG).show()
//        }
//    }



}