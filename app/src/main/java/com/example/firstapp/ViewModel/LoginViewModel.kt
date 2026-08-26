package com.example.firstapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.Model.LoginModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(value: String) { email = value; errorMessage = null }
    fun onPasswordChange(value: String) { password = value; errorMessage = null }

    fun login(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Veuillez remplir tous les champs"
            return
        }

        val model = LoginModel(email = email, password = password)

        viewModelScope.launch {
            isLoading = true
            // TODO: remplacer par un appel Retrofit vers l'API Django, ex:
            // val response = apiService.login(model)
            // if (response.isSuccessful) onSuccess() else errorMessage = "Identifiants invalides"
            delay(800)
            isLoading = false
            onSuccess()
        }
    }
}
