package com.example.firstapp.Model

data class RegisterModel(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)
