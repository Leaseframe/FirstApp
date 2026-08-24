package com.example.firstapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firstapp.model.StartModel

class StartViewModel : ViewModel() {
    val msg = StartModel()

    fun getMessage() : String{
        return msg.message
    }
}