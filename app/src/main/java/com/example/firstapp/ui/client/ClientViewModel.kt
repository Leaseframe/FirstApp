package com.example.firstapp.ui.client

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel : Gère l'état de l'écran Client et contient la logique métier.
 * Il expose les données via StateFlow pour que la View puisse les observer.
 * Les données sont ici statiques (Mock data) pour le besoin de l'exercice.
 */
class ClientViewModel : ViewModel() {

    // État interne (Mutable)
    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    // État exposé à la View (Immutable)
    val clients: StateFlow<List<Client>> = _clients.asStateFlow()

    init {
        chargerClients()
    }

    private fun chargerClients() {
        // Simulation de chargement de données statiques
        _clients.value = listOf(
            Client(1, "Jean Dupont", "Logistics SA", "01 23 45 67 89", "12 Rue de Paris, Paris", true),
            Client(2, "Marie Durand", "TransExpress", "02 34 56 78 90", "45 Ave des Lys, Lyon", true),
            Client(3, "Pierre Martin", "Global Ship", "03 45 67 89 01", "8 Bis Route de Brest, Rennes", false),
            Client(4, "Sophie Bernard", "FastDelivery", "04 56 78 90 12", "102 Chemin Vert, Marseille", true),
            Client(5, "Lucas Petit", "EcoFret", "05 67 89 01 23", "22 Place Royale, Nantes", false)
        )
    }
}
