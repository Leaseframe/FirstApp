package com.example.firstapp.Model


data class UtilisateurProfil(
    val nom: String,
    val role: String,
    val email: String,
    val telephone : String,
    val ville : String,
    val membreDepuis: String,
    val photoUrl: String? = null
)
