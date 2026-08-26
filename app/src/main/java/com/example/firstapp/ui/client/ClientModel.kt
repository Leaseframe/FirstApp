package com.example.firstapp.ui.client

/**
 * Model : Représente les données pures de l'entité Client.
 * C'est une simple classe de données (data class) sans logique.
 */
data class Client(
    val id: Int,
    val nom: String,
    val entreprise: String,
    val telephone: String,
    val adresse: String,
    val estActif: Boolean
)
