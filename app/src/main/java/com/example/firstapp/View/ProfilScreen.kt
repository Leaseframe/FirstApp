package com.example.firstapp.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstapp.Model.UtilisateurProfil
import com.example.firstapp.ViewModel.ProfilViewModel

/**
 * VIEW / SCREEN (couche View du MVVM)
 * -------------------------------------
 * Affiche la carte d'identité, les informations personnelles et la
 * sécurité du compte. N'affiche QUE du contenu, aucune logique métier :
 * tout vient du ProfilViewModel (état observé, actions déléguées).
 */
@Composable
fun ProfilScreen(viewModel: ProfilViewModel = viewModel()) {
    val utilisateur by viewModel.utilisateur.collectAsState()
    val afficherDialogue by viewModel.afficherDialogueDeconnexion.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CarteIdentite(utilisateur)
        CarteInformationsPersonnelles(utilisateur)
        CarteSecurite()

        // Bouton de déconnexion : visuel uniquement, aucune logique fonctionnelle réelle
        Button(
            onClick = { viewModel.demanderDeconnexion() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Filled.Logout, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Se déconnecter")
        }
    }

    if (afficherDialogue) {
        AlertDialog(
            onDismissRequest = { viewModel.annulerDeconnexion() },
            title = { Text("Déconnexion") },
            text = { Text("Voulez-vous vraiment vous déconnecter ?") },
            confirmButton = {
                TextButton(onClick = { viewModel.confirmerDeconnexion() }) {
                    Text("Se déconnecter")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.annulerDeconnexion() }) {
                    Text("Annuler")
                }
            }
        )
    }
}

/** Carte du haut : photo, nom, rôle et coordonnées principales. */
@Composable
private fun CarteIdentite(utilisateur: UtilisateurProfil) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(72.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Photo de profil",
                        modifier = Modifier.size(36.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = utilisateur.nom,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = utilisateur.role,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LigneIcone(icon = Icons.Filled.Email, texte = utilisateur.email)
                LigneIcone(icon = Icons.Filled.Phone, texte = utilisateur.telephone)
                LigneIcone(icon = Icons.Filled.LocationOn, texte = utilisateur.ville)
                LigneIcone(
                    icon = Icons.Filled.CalendarToday,
                    texte = "Membre depuis ${utilisateur.membreDepuis}"
                )
            }
        }
    }
}

/** Carte "Informations personnelles" : liste de champs label / valeur. */
@Composable
private fun CarteInformationsPersonnelles(utilisateur: UtilisateurProfil) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Informations personnelles",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                // Visuel uniquement : l'édition des champs n'est pas implémentée à ce stade
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Modifier",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ChampInfo(label = "Nom complet", valeur = utilisateur.nom)
            HorizontalDivider()
            ChampInfo(label = "Email", valeur = utilisateur.email)
            HorizontalDivider()
            ChampInfo(label = "Téléphone", valeur = utilisateur.telephone)
            HorizontalDivider()
            ChampInfo(label = "Fonction", valeur = utilisateur.role, navigable = true)
            HorizontalDivider()
            ChampInfo(label = "Ville", valeur = utilisateur.ville)
        }
    }
}

/** Carte "Sécurité du compte" : accès (visuel) au changement de mot de passe. */
@Composable
private fun CarteSecurite() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sécurité du compte",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Icon(Icons.Filled.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // Visuel uniquement : aucune navigation ni logique de changement de mot de passe
                    .clickable { }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Changer le mot de passe", style = MaterialTheme.typography.bodyLarge)
                Icon(Icons.Filled.ChevronRight, contentDescription = null)
            }
        }
    }
}

@Composable
private fun ChampInfo(label: String, valeur: String, navigable: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = valeur, style = MaterialTheme.typography.bodyMedium)
            if (navigable) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun LigneIcone(icon: ImageVector, texte: String) {
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
        Text(text = texte, style = MaterialTheme.typography.bodySmall)
    }
}
