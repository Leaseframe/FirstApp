package com.example.firstapp.Model


enum class TripStatus(val label: String) {
    IN_PROGRESS("En cours"),
    DELIVERED("Livré")
}

enum class StopType {
    DEPARTURE,
    INTERMEDIATE,
    ARRIVAL
}

data class RouteStop(
    val city: String,
    val detail: String,
    val type: StopType
)

data class Driver(
    val initials: String,
    val name: String,
    val role: String,
    val vehicleNumber: String
)

data class TripDetail(
    val distance: String,
    val estimatedDuration: String,
    val status: TripStatus,
    val stops: List<RouteStop>,
    val driver: Driver
)