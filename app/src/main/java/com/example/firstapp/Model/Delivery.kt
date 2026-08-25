package com.example.firstapp.Model

data class Delivery(
    val id: String,
    val client: String,
    val vehicle: String,
    val departure: String,
    val destination: String,
    val merchandise: String,
    val status: DeliveryStatus
)
