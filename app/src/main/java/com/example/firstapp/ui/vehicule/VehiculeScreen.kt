package com.example.firstapp.ui.vehicule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * View : Affiche la liste des véhicules.
 * Utilise Material 3 pour un design moderne et cohérent.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiculeScreen(viewModel: VehiculeViewModel = viewModel()) {
    val vehicules by viewModel.vehicules.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parc Automobile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(vehicules) { vehicule ->
                VehiculeCard(vehicule)
            }
        }
    }
}

@Composable
fun VehiculeCard(vehicule: Vehicule) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = vehicule.immatriculation,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                StatutBadge(vehicule.statut)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(text = "Type : ${vehicule.type}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Capacité : ${vehicule.capacite}", style = MaterialTheme.typography.bodyMedium)
            
            Divider(modifier = Modifier.padding(vertical = 8.dp), color = MaterialTheme.colorScheme.outlineVariant)
            
            Text(
                text = "Chauffeur : ${vehicule.chauffeur}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun StatutBadge(statut: StatutVehicule) {
    val backgroundColor = when (statut) {
        StatutVehicule.DISPONIBLE -> Color(0xFF4CAF50)
        StatutVehicule.EN_LIVRAISON -> Color(0xFF2196F3)
        StatutVehicule.MAINTENANCE -> Color(0xFFFF9800)
    }
    
    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = statut.label.uppercase(),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
