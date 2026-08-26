package com.example.firstapp.model

data class Chauffeur(
    val id: String,
    val nom: String,
    val telephone: String,
    val vehiculeAssigne: String,
    val statut: StatutChauffeur,
)

enum class StatutChauffeur(val libelle: String) {
    DISPONIBLE("Disponible"),
    EN_ROUTE("En route"),
    HORS_SERVICE("Hors service")
}
