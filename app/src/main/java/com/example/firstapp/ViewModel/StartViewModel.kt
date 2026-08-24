package com.example.firstapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.firstapp.Model.StartModel

class StartViewModel : ViewModel() {
    val msg = StartModel()

    fun getMessage() : String{
        return msg.message
    }
}