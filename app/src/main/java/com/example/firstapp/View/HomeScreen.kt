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