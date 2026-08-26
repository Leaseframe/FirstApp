package com.example.firstapp.Model


data class Vehicule(
    val id: Int,
    val immatriculation: String,
    val type: String,
    val capacite: String,
    val statut: StatutVehicule,
    val chauffeurAssigne: String?
)


enum class StatutVehicule {
    DISPONIBLE,
    EN_LIVRAISON,
    EN_MAINTENANCE
}
