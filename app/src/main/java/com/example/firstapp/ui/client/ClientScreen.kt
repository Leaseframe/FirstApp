package com.example.firstapp.ui.client

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
 * View (Composable) : Gère uniquement l'affichage des clients.
 * Elle observe l'état du ViewModel et n'effectue aucune logique métier.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(viewModel: ClientViewModel = viewModel()) {
    // Collecte de l'état du ViewModel réactif
    val clients by viewModel.clients.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestion des Clients") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(clients) { client ->
                ClientCard(client)
            }
        }
    }
}

@Composable
fun ClientCard(client: Client) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                    text = client.nom,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                StatusBadge(client.estActif)
            }
            Text(text = client.entreprise, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "📞 ${client.telephone}", style = MaterialTheme.typography.bodySmall)
            Text(text = "📍 ${client.adresse}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun StatusBadge(isActive: Boolean) {
    Surface(
        color = if (isActive) Color(0xFF4CAF50) else Color(0xFFF44336),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = if (isActive) "ACTIF" else "INACTIF",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
    }
}
