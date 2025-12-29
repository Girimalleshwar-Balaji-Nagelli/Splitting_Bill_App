package com.example.splittingbillapp

import com.example.splittingbillapp.Models.Message

data class ExpandableMessage(
    val message: Message,
    var isExpanded: Boolean = false
)