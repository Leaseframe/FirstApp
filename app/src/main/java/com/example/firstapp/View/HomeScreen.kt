<<<<<<< HEAD
package com.example.firstapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.ViewModel.HomeViewModel
import com.example.firstapp.ui.theme.BleuBic
import com.example.firstapp.ui.theme.BleuClair
import com.example.firstapp.ui.theme.orange

@Composable
fun HomeScreen(
    vm: HomeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // --- Icône principale ---
        Column(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = BleuClair,
                    shape = CircleShape
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Inventory,
                contentDescription = "Gestion des stocks",
                tint = Color.White,
                modifier = Modifier.size(52.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Titre ---
        Text(
            text = vm.title,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = orange
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Sous-titre ---
        Text(
            text = vm.subtitle,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = BleuBic
        )

        Spacer(modifier = Modifier.height(12.dp))

        // --- Message ---
        Text(
            text = "Gérez facilement vos commandes, stocks et livraisons.",
            fontSize = 14.sp,
            color = BleuBic,
            textAlign = TextAlign.Center
        )

        // TODO:
        // Ajouter ici les modules :
        // - Commandes
        // - Livraisons
        // - Stocks
        // - Clients
        // - Statistiques
    }
}
=======
package com.example.firstapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstapp.viewmodel.HomeViewModel

/**
 * VIEW (couche Compose) : n'a AUCUNE logique métier.
 * Elle observe le HomeViewModel et ne fait que "dessiner" ce qu'il expose.
 * Toute interaction (clic) est déléguée au ViewModel via ses fonctions publiques.
 */
@Composable
fun HomeScreen(
    nomEntreprise: String = "TRANSGLOBE",
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            HomeTopBar(
                nomEntreprise = nomEntreprise,
                nombreNotifications = uiState.nombreNotifications,
                onNotificationClick = viewModel::onNotificationClick
            )
        },
        bottomBar = {
            HomeBottomBar(
                selectedTab = selectedTab,
                onTabSelected = viewModel::onTabSelected
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.errorMessage ?: "Erreur inconnue")
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { SectionTitle(titre = "Chauffeurs", icone = Icons.Default.Person) }
                    items(uiState.chauffeurs, key = { it.id }) { chauffeur ->
                        ChauffeurCard(chauffeur)
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item { SectionTitle(titre = "Marchandises", icone = Icons.Default.Inventory2) }
                    items(uiState.marchandises, key = { it.id }) { marchandise ->
                        MarchandiseCard(marchandise)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}
>>>>>>> origin/group2
