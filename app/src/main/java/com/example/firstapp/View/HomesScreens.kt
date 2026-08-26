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
import com.example.firstapp.viewmodel.HomesViewModels

/**
 * VIEW (couche Compose) : n'a AUCUNE logique métier.
 * Elle observe le HomeViewModel et ne fait que "dessiner" ce qu'il expose.
 * Toute interaction (clic) est déléguée au ViewModel via ses fonctions publiques.
 */
@Composable
fun HomesScreens(
    nomEntreprise: String = "TRANSGLOBE",
    vm: HomesViewModels = viewModel(),
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val selectedTab by vm.selectedTab.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            HomeTopBar(
                nomEntreprise = nomEntreprise,
                nombreNotifications = uiState.nombreNotifications,
                onNotificationClick = vm::onNotificationClick
            )
        },
//        bottomBar = {
//            HomeBottomBar(
//                selectedTab = selectedTab,
//                onTabSelected = vm::onTabSelected
//            )
//        }
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
        HomesScreens()
    }
}
