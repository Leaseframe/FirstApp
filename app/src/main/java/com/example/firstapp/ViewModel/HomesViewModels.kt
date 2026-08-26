package com.example.firstapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.model.Chauffeur
import com.example.firstapp.model.Marchandise
import com.example.firstapp.model.StatutChauffeur
import com.example.firstapp.model.StatutMarchandise
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Etat de l'écran Home exposé à la View.
 * La View ne fait que "lire" cet état, jamais le modifier directement.
 */
data class HomeUiState(
    val isLoading: Boolean = true,
    val chauffeurs: List<Chauffeur> = emptyList(),
    val marchandises: List<Marchandise> = emptyList(),
    val nombreNotifications: Int = 0,
    val errorMessage: String? = null
)

/**
 * Onglets de la bottom bar : état de navigation propre à l'écran Home.
 */
enum class HomeTab(val label: String) {
    HOME("Accueil"),
    CLIENT("Client"),
    VEHICULE("Véhicule"),
    PROFILE("Profil")
}

/**
 * ViewModel = couche intermédiaire entre Model et View.
 * Ici, sans repository : c'est lui qui "possède" et prépare les données.
 */
class HomesViewModels : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _selectedTab = MutableStateFlow(HomeTab.HOME)
    val selectedTab: StateFlow<HomeTab> = _selectedTab.asStateFlow()

    init {
        chargerDonnees()
    }

    fun chargerDonnees() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                delay(300) // simule un temps de chargement

                val chauffeurs = listOf(
                    Chauffeur(
                        id = "c1",
                        nom = "Mamadou Diallo",
                        telephone = "+224 621 00 11 22",
                        vehiculeAssigne = "Camion - GN 4521 A",
                        statut = StatutChauffeur.EN_ROUTE
                    ),
                    Chauffeur(
                        id = "c2",
                        nom = "Fatoumata Camara",
                        telephone = "+224 655 33 44 55",
                        vehiculeAssigne = "Camionnette - GN 1187 B",
                        statut = StatutChauffeur.DISPONIBLE
                    )
                )

                val marchandises = listOf(
                    Marchandise(
                        id = "m1",
                        nom = "Sacs de ciment",
                        poids = "2 500 kg",
                        destination = "Kindia",
                        statut = StatutMarchandise.EN_LIVRAISON
                    ),
                    Marchandise(
                        id = "m2",
                        nom = "Matériel électronique",
                        poids = "800 kg",
                        destination = "Kankan",
                        statut = StatutMarchandise.EN_ATTENTE
                    )
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    chauffeurs = chauffeurs,
                    marchandises = marchandises,
                    nombreNotifications = 3
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Impossible de charger les données : ${e.message}"
                )
            }
        }
    }

    fun onTabSelected(tab: HomeTab) {
        _selectedTab.value = tab
    }

    fun onNotificationClick() {
        // TODO: naviguer vers l'écran des notifications
    }
}
