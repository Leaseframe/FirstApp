package com.example.firstapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.firstapp.Model.Client
import com.example.firstapp.Model.StatutClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ClientViewModel : ViewModel() {

    // État privé et mutable, modifiable uniquement depuis le ViewModel
    private val _clients = MutableStateFlow(genererClientsFictifs())

    // État public et immuable, exposé en lecture seule à la vue
    val clients: StateFlow<List<Client>> = _clients.asStateFlow()

    private fun genererClientsFictifs(): List<Client> = listOf(
        Client(
            id = 1,
            nom = "Jean Dupont",
            entreprise = "Transport Dupont SARL",
            telephone = "06 12 34 56 78",
            adresse = "12 rue des Lilas, Paris",
            statut = StatutClient.ACTIF
        ),
        Client(
            id = 2,
            nom = "Marie Lambert",
            entreprise = "Lambert Logistique",
            telephone = "06 98 76 54 32",
            adresse = "5 avenue Victor Hugo, Lyon",
            statut = StatutClient.ACTIF
        ),
        Client(
            id = 3,
            nom = "Paul Bernard",
            entreprise = "Bernard Import-Export",
            telephone = "07 11 22 33 44",
            adresse = "8 rue de la Gare, Marseille",
            statut = StatutClient.INACTIF
        ),
        Client(
            id = 4,
            nom = "Sophie Martin",
            entreprise = "Martin Distribution",
            telephone = "06 55 44 33 22",
            adresse = "20 boulevard Haussmann, Paris",
            statut = StatutClient.ACTIF
        ),
        Client(
            id = 5,
            nom = "Luc Moreau",
            entreprise = "Moreau Fret Express",
            telephone = "07 66 77 88 99",
            adresse = "3 place Bellecour, Lyon",
            statut = StatutClient.INACTIF
        ),
        Client(
            id = 6,
            nom = "Claire Petit",
            entreprise = "Petit Cargo Services",
            telephone = "06 44 55 66 77",
            adresse = "15 rue Nationale, Lille",
            statut = StatutClient.ACTIF
        )
    )
}
