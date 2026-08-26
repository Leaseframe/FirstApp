package com.example.firstapp.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.viewmodel.HomeViewModel

@Composable
fun AppNavigation(){
    val nC = rememberNavController()

    NavHost(
        navController = nC,
        startDestination = "home",
    ) {
        composable("start"){
            val viewModel: StartViewModel = viewModel()
            StartScreen(
                vm = viewModel
            )
        }
        composable("home"){
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = viewModel
            )
        }
    }
}