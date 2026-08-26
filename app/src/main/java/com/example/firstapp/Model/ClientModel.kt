package com.example.firstapp.Model


data class Client(
    val id: Int,
    val nom: String,
    val entreprise: String,
    val telephone: String,
    val adresse: String,
    val statut: StatutClient
)


enum class StatutClient {
    ACTIF,
    INACTIF
}
