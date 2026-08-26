package com.example.firstapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
<<<<<<< HEAD
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
=======
>>>>>>> origin/group2
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
<<<<<<< HEAD
import com.example.firstapp.ViewModel.StartViewModel
import com.example.firstapp.ui.theme.orange
import com.example.firstapp.ui.theme.BleuBic
import com.example.firstapp.ui.theme.BleuClair
=======
import com.example.firstapp.viewmodel.StartViewModel
>>>>>>> origin/group2

@Composable
fun StartScreen(
    vm: StartViewModel,
<<<<<<< HEAD
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
=======
){
    val messages = vm.getMessage()

>>>>>>> origin/group2
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        // --- Logo de l'entreprise ---
        Column(
            modifier = Modifier
                .size(110.dp)
                .background(
                    color = BleuClair,
                    shape = CircleShape
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.LocalShipping,
                contentDescription = "Logo entreprise",
                tint = Color.White,
                modifier = Modifier.size(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Nom de l'application ---
        Text(
<<<<<<< HEAD
            text = vm.appName,
=======
            text = messages,
>>>>>>> origin/group2
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = orange
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Message de bienvenue ---
        Text(
            text = vm.welcomeMessage,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = BleuBic
        )

        Spacer(modifier = Modifier.height(12.dp))

        // --- Description ---
        Text(
            text = vm.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = BleuBic
        )

        Spacer(modifier = Modifier.height(40.dp))

        // --- Bouton Se connecter ---
        Button(
            onClick = onNavigateToLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Se connecter",
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // --- Bouton Inscription ---
        OutlinedButton(
            onClick = onNavigateToRegister,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BleuBic
            )
        ) {
            Text(
                text = "S'inscrire",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}