package com.example.firstapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstapp.Model.StatutVehicule
import com.example.firstapp.Model.Vehicule
import com.example.firstapp.ViewModel.VehiculeViewModel

@Composable
fun VehiculeScreen(viewModel: VehiculeViewModel = viewModel()) {
    val vehicules by viewModel.vehicules.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(vehicules, key = { it.id }) { vehicule ->
            VehiculeCard(vehicule = vehicule)
        }
    }
}

@Composable
private fun VehiculeCard(vehicule: Vehicule) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = vehicule.immatriculation,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                StatutVehiculeBadge(statut = vehicule.statut)
            }

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(icon = Icons.Filled.LocalShipping, text = vehicule.type)
            InfoRow(icon = Icons.Filled.Scale, text = "Capacité : ${vehicule.capacite}")
            InfoRow(
                icon = Icons.Filled.Person,
                text = vehicule.chauffeurAssigne ?: "Aucun chauffeur assigné"
            )
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun StatutVehiculeBadge(statut: StatutVehicule) {
    val (label, color) = when (statut) {
        StatutVehicule.DISPONIBLE -> "Disponible" to Color(0xFF2E7D32)
        StatutVehicule.EN_LIVRAISON -> "En livraison" to Color(0xFF1565C0)
        StatutVehicule.EN_MAINTENANCE -> "En maintenance" to Color(0xFFC62828)
    }
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color.copy(alpha = 0.15f)
    ) {
        Text(
            text = label,
            color = color,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
