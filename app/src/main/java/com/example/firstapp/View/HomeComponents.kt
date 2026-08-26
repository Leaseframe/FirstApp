package com.example.firstapp.view



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.R
import com.example.firstapp.model.Chauffeur
import com.example.firstapp.model.Marchandise
import com.example.firstapp.viewmodel.HomeTab

// Tous les composables ci-dessous sont "stateless" : ils reçoivent des données
// et des callbacks en paramètres, et n'accèdent JAMAIS au ViewModel ou au repository.
// C'est ce découplage qui respecte la couche "View" du MVVM.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    nomEntreprise: String,
    nombreNotifications: Int,
    onNotificationClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.logo_transglobe),
//                        contentDescription = "Logo TRANSGLOBE",
//                        modifier = Modifier.fillMaxSize()
//                    )
                    Icon(
                        imageVector = Icons.Filled.Inventory,
                        contentDescription = "Gestion des stocks",
                        tint = Color.White,
                        modifier = Modifier.size(52.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = nomEntreprise, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        },
        actions = {
            Box {
                IconButton(onClick = onNotificationClick) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                }
                if (nombreNotifications > 0) {
                    Badge(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 6.dp, end = 6.dp)
                    ) {
                        Text(text = nombreNotifications.toString())
                    }
                }
            }
        }
    )
}

@Composable
fun HomeBottomBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == HomeTab.HOME,
            onClick = { onTabSelected(HomeTab.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = HomeTab.HOME.label) },
            label = { Text(HomeTab.HOME.label) }
        )
        NavigationBarItem(
            selected = selectedTab == HomeTab.CLIENT,
            onClick = { onTabSelected(HomeTab.CLIENT) },
            icon = { Icon(Icons.Default.People, contentDescription = HomeTab.CLIENT.label) },
            label = { Text(HomeTab.CLIENT.label) }
        )
        NavigationBarItem(
            selected = selectedTab == HomeTab.VEHICULE,
            onClick = { onTabSelected(HomeTab.VEHICULE) },
            icon = { Icon(Icons.Default.LocalShipping, contentDescription = HomeTab.VEHICULE.label) },
            label = { Text(HomeTab.VEHICULE.label) }
        )
        NavigationBarItem(
            selected = selectedTab == HomeTab.PROFILE,
            onClick = { onTabSelected(HomeTab.PROFILE) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = HomeTab.PROFILE.label) },
            label = { Text(HomeTab.PROFILE.label) }
        )
    }
}

@Composable
fun SectionTitle(titre: String, icone: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    ) {
        Icon(
            imageVector = icone,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = titre, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun ChauffeurCard(chauffeur: Chauffeur) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = chauffeur.nom, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = chauffeur.telephone, fontSize = 13.sp, color = Color.Gray)
                Text(text = chauffeur.vehiculeAssigne, fontSize = 13.sp, color = Color.Gray)
            }
            StatutBadge(libelle = chauffeur.statut.libelle)
        }
    }
}

@Composable
fun MarchandiseCard(marchandise: Marchandise) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Inventory2, contentDescription = null, tint = MaterialTheme.colorScheme.onTertiaryContainer)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = marchandise.nom, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = "Poids : ${marchandise.poids}", fontSize = 13.sp, color = Color.Gray)
                Text(text = "Destination : ${marchandise.destination}", fontSize = 13.sp, color = Color.Gray)
            }
            StatutBadge(libelle = marchandise.statut.libelle)
        }
    }
}

@Composable
fun StatutBadge(libelle: String) {
    val couleur = when (libelle) {
        "Disponible", "Livrée" -> Color(0xFF2E7D32)
        "En route", "En livraison" -> Color(0xFFF9A825)
        else -> Color(0xFFC62828)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(couleur.copy(alpha = 0.15f))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = libelle, color = couleur, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}
