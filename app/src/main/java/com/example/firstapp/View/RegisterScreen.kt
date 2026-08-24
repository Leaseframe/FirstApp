package com.example.firstapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.ViewModel.RegisterViewModel
import com.example.firstapp.ui.theme.BleuBic
import com.example.firstapp.ui.theme.BleuClair
import com.example.firstapp.ui.theme.orange

@Composable
fun RegisterScreen(
    vm: RegisterViewModel,
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {

    // Mot de passe
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    // Confirmation du mot de passe
    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // LOGO
        Column(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = BleuClair,
                    shape = CircleShape
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.LocalShipping,
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier.size(42.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TITRE
        Text(
            text = "Inscription",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = orange
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Créez votre compte",
            fontSize = 15.sp,
            color = BleuBic
        )

        Spacer(modifier = Modifier.height(24.dp))

        // NOM COMPLET
        OutlinedTextField(
            value = vm.fullName,
            onValueChange = vm::onFullNameChange,
            label = {
                Text("Nom complet")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = orange,
                unfocusedBorderColor = BleuBic,
                focusedLabelColor = orange,
                unfocusedLabelColor = BleuBic,
                cursorColor = orange
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // EMAIL
        OutlinedTextField(
            value = vm.email,
            onValueChange = vm::onEmailChange,
            label = {
                Text("Email")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = orange,
                unfocusedBorderColor = BleuBic,
                focusedLabelColor = orange,
                unfocusedLabelColor = BleuBic,
                cursorColor = orange
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // MOT DE PASSE
        OutlinedTextField(
            value = vm.password,
            onValueChange = vm::onPasswordChange,
            label = {
                Text("Mot de passe")
            },
            singleLine = true,

            visualTransformation =
                if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector =
                            if (passwordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                        contentDescription =
                            if (passwordVisible) {
                                "Masquer le mot de passe"
                            } else {
                                "Afficher le mot de passe"
                            },
                        tint = BleuBic
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(12.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = orange,
                unfocusedBorderColor = BleuBic,
                focusedLabelColor = orange,
                unfocusedLabelColor = BleuBic,
                cursorColor = orange
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // CONFIRMATION MOT DE PASSE
        OutlinedTextField(
            value = vm.confirmPassword,
            onValueChange = vm::onConfirmPasswordChange,
            label = {
                Text("Confirmer le mot de passe")
            },
            singleLine = true,

            visualTransformation =
                if (confirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

            trailingIcon = {
                IconButton(
                    onClick = {
                        confirmPasswordVisible = !confirmPasswordVisible
                    }
                ) {
                    Icon(
                        imageVector =
                            if (confirmPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                        contentDescription =
                            if (confirmPasswordVisible) {
                                "Masquer la confirmation"
                            } else {
                                "Afficher la confirmation"
                            },
                        tint = BleuBic
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(12.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = orange,
                unfocusedBorderColor = BleuBic,
                focusedLabelColor = orange,
                unfocusedLabelColor = BleuBic,
                cursorColor = orange
            )
        )

        // ERREUR
        vm.errorMessage?.let { error ->

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = error,
                color = Color.Red,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // BOUTON INSCRIPTION
        Button(
            onClick = {
                vm.register(
                    onSuccess = onRegisterSuccess
                )
            },
            enabled = !vm.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
                contentColor = Color.White
            )
        ) {

            if (vm.isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    text = "S'inscrire",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // RETOUR LOGIN
        TextButton(
            onClick = onNavigateToLogin
        ) {
            Text(
                text = "Déjà un compte ? Se connecter",
                color = BleuBic,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}