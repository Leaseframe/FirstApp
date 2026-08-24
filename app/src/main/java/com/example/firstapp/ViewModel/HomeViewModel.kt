package com.example.firstapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.firstapp.Model.HomeModel

class HomeViewModel : ViewModel() {
    private val data = HomeModel()

    val title: String get() = data.title
    val subtitle: String get() = data.subtitle
}
