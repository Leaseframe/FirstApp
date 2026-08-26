package com.example.firstapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firstapp.model.StartModel

class StartViewModel : ViewModel() {

    private val data = StartModel()

    val appName: String get() = data.appName
    val welcomeMessage: String get() = data.welcomeMessage
    val description: String get() = data.description
}
