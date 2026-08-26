package com.example.firstapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.firstapp.Model.StatutVehicule
import com.example.firstapp.Model.Vehicule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class VehiculeViewModel : ViewModel() {

    private val _vehicules = MutableStateFlow(genererVehiculesFictifs())
    val vehicules: StateFlow<List<Vehicule>> = _vehicules.asStateFlow()


    private fun genererVehiculesFictifs(): List<Vehicule> = listOf(
        Vehicule(
            id = 1,
            immatriculation = "AB-123-CD",
            type = "Camion 19T",
            capacite = "12 palettes",
            statut = StatutVehicule.EN_LIVRAISON,
            chauffeurAssigne = "Karim Haddad"
        ),
        Vehicule(
            id = 2,
            immatriculation = "EF-456-GH",
            type = "Fourgon",
            capacite = "3,5 tonnes",
            statut = StatutVehicule.DISPONIBLE,
            chauffeurAssigne = null
        ),
        Vehicule(
            id = 3,
            immatriculation = "IJ-789-KL",
            type = "Semi-remorque",
            capacite = "24 palettes",
            statut = StatutVehicule.EN_MAINTENANCE,
            chauffeurAssigne = null
        ),
        Vehicule(
            id = 4,
            immatriculation = "MN-321-OP",
            type = "Camionnette",
            capacite = "1,2 tonnes",
            statut = StatutVehicule.DISPONIBLE,
            chauffeurAssigne = null
        ),
        Vehicule(
            id = 5,
            immatriculation = "QR-654-ST",
            type = "Camion 12T",
            capacite = "8 palettes",
            statut = StatutVehicule.EN_LIVRAISON,
            chauffeurAssigne = "Nadia Fontaine"
        ),
        Vehicule(
            id = 6,
            immatriculation = "UV-987-WX",
            type = "Fourgon frigorifique",
            capacite = "2 tonnes",
            statut = StatutVehicule.DISPONIBLE,
            chauffeurAssigne = "Thomas Girard"
        )
    )
}
