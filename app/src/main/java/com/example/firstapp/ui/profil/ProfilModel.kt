package com.example.firstapp.ui.profil

/**
 * Model : Représente les informations de l'utilisateur connecté.
 */
data class Profil(
    val nom: String,
    val role: String,
    val email: String,
    val photoUrl: String? = null
)
