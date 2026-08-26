package com.example.firstapp.ui.vehicule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel : Orchestre les données pour la vue Véhicule.
 * Il maintient l'état de la liste des véhicules de manière réactive.
 */
class VehiculeViewModel : ViewModel() {

    private val _vehicules = MutableStateFlow<List<Vehicule>>(emptyList())
    val vehicules: StateFlow<List<Vehicule>> = _vehicules.asStateFlow()

    init {
        chargerVehicules()
    }

    private fun chargerVehicules() {
        // Mock data : 5 véhicules fictifs
        _vehicules.value = listOf(
            Vehicule(1, "AA-123-BB", "Camion Frigo", "12 Tonnes", StatutVehicule.DISPONIBLE, "Marc Martin"),
            Vehicule(2, "CC-456-DD", "Utilitaire L2H2", "1.5 Tonne", StatutVehicule.EN_LIVRAISON, "Alice Dubois"),
            Vehicule(3, "EE-789-FF", "Poids Lourd", "24 Tonnes", StatutVehicule.MAINTENANCE, "Paul Lefebvre"),
            Vehicule(4, "GG-012-HH", "Camion Plateau", "10 Tonnes", StatutVehicule.DISPONIBLE, "Julien Clerc"),
            Vehicule(5, "II-345-JJ", "Fourgonnette", "800 Kg", StatutVehicule.EN_LIVRAISON, "Sophie Marceau")
        )
    }
}
