package com.example.firstapp.View

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.ViewModel.StartViewModel

@Composable
fun AppNavigation(){
    val nC = rememberNavController()

    NavHost(
        navController = nC,
        startDestination = "start"
    ) {
        composable("start"){
            val viewModel: StartViewModel = viewModel()
            StartScreen(
                vm = viewModel
            )
        }
    }
}