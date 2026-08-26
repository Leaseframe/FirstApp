package com.example.firstapp.ui.profil

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel : Gère les données du profil utilisateur.
 */
class ProfilViewModel : ViewModel() {

    private val _profil = MutableStateFlow<Profil?>(null)
    val profil: StateFlow<Profil?> = _profil.asStateFlow()

    init {
        chargerProfil()
    }

    private fun chargerProfil() {
        // Données utilisateur fictives
        _profil.value = Profil(
            nom = "Diane Developpeuse",
            role = "Gestionnaire Logistique Senior",
            email = "diane.dev@logistics-pro.com",
            photoUrl = null // Utilisation d'un placeholder dans la View
        )
    }
    
    fun deconnecter() {
        // Logique de déconnexion (visuelle uniquement ici)
        println("Déconnexion demandée")
    }
}
