package com.example.firstapp.ui.vehicule

/**
 * Model : Représente les données pures de l'entité Véhicule.
 * Cette classe définit la structure d'un véhicule dans le système.
 */
data class Vehicule(
    val id: Int,
    val immatriculation: String,
    val type: String,
    val capacite: String,
    val statut: StatutVehicule,
    val chauffeur: String
)

/**
 * Enumération pour gérer les différents états d'un véhicule.
 */
enum class StatutVehicule(val label: String) {
    DISPONIBLE("Disponible"),
    EN_LIVRAISON("En livraison"),
    MAINTENANCE("En maintenance")
}
