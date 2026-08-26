package com.example.firstapp.model

data class Marchandise(
    val id: String,
    val nom: String,
    val poids: String,
    val destination: String,
    val statut: StatutMarchandise,
)

enum class StatutMarchandise(val libelle: String) {
    EN_ATTENTE("En attente"),
    EN_LIVRAISON("En livraison"),
    LIVREE("Livrée")
}

