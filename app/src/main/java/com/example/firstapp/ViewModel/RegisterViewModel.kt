package com.example.firstapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.Model.RegisterModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var fullName by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onFullNameChange(value: String) { fullName = value; errorMessage = null }
    fun onEmailChange(value: String) { email = value; errorMessage = null }
    fun onPasswordChange(value: String) { password = value; errorMessage = null }
    fun onConfirmPasswordChange(value: String) { confirmPassword = value; errorMessage = null }

    fun register(onSuccess: () -> Unit) {
        if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            errorMessage = "Veuillez remplir tous les champs"
            return
        }
        if (password != confirmPassword) {
            errorMessage = "Les mots de passe ne correspondent pas"
            return
        }

        val model = RegisterModel(
            fullName = fullName,
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )

        viewModelScope.launch {
            isLoading = true
            // TODO: remplacer par un appel Retrofit vers l'API Django, ex:
            // val response = apiService.register(model)
            // if (response.isSuccessful) onSuccess() else errorMessage = "Erreur lors de l'inscription"
            delay(800)
            isLoading = false
            onSuccess()
        }
    }
}
