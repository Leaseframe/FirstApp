package com.example.firstapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.firstapp.Model.UtilisateurProfil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfilViewModel : ViewModel() {

    private val _utilisateur = MutableStateFlow(
        UtilisateurProfil(
            nom = "Amine Cherif",
            role = "Gestionnaire Logistique",
            email = "amine.cherif@logistique-app.fr",
            telephone = "+224 627 56 87 90",
            ville = "Paris",
            membreDepuis = "Juin 2024",
            photoUrl = null
        )
    )
    val utilisateur: StateFlow<UtilisateurProfil> = _utilisateur.asStateFlow()

    // État visuel : la boîte de dialogue de confirmation de déconnexion est-elle affichée ?
    private val _afficherDialogueDeconnexion = MutableStateFlow(false)
    val afficherDialogueDeconnexion: StateFlow<Boolean> = _afficherDialogueDeconnexion.asStateFlow()


    fun demanderDeconnexion() {
        _afficherDialogueDeconnexion.value = true
    }


    fun annulerDeconnexion() {
        _afficherDialogueDeconnexion.value = false
    }


    fun confirmerDeconnexion() {
        _afficherDialogueDeconnexion.value = false

    }
}
