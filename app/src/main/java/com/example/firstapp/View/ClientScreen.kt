package com.example.firstapp.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
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
import com.example.firstapp.Model.Client
import com.example.firstapp.Model.StatutClient
import com.example.firstapp.ViewModel.ClientViewModel

/**
 * VIEW / SCREEN (couche View du MVVM)
 * -------------------------------------
 * N'affiche QUE le contenu (la liste des clients).
 * Le Scaffold + la TopAppBar sont désormais gérés une seule fois par
 * AppScaffold (barre commune, titre dynamique selon l'écran actif),
 * pour éviter de dupliquer cette barre dans chaque écran.
 */
@Composable
fun ClientScreen(viewModel: ClientViewModel = viewModel()) {
    val clients by viewModel.clients.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(clients, key = { it.id }) { client ->
            ClientCard(client = client)
        }
    }
}

/**
 * Sous-composant purement visuel affichant un client dans une Card.
 * Ne reçoit que des données en paramètre (pas d'accès au ViewModel).
 */
@Composable
private fun ClientCard(client: Client) {
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
                    text = client.nom,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                StatutBadge(statut = client.statut)
            }

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(icon = Icons.Filled.Business, text = client.entreprise)
            InfoRow(icon = Icons.Filled.Phone, text = client.telephone)
            InfoRow(icon = Icons.Filled.LocationOn, text = client.adresse)
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
private fun StatutBadge(statut: StatutClient) {
    val (label, color) = when (statut) {
        StatutClient.ACTIF -> "Actif" to Color(0xFF2E7D32)
        StatutClient.INACTIF -> "Inactif" to Color(0xFF9E9E9E)
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
