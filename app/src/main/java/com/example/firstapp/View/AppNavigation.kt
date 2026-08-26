package com.example.firstapp.View

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.Dashboard
import com.example.firstapp.ViewModel.HomeViewModel
import com.example.firstapp.ViewModel.LoginViewModel
import com.example.firstapp.ViewModel.RegisterViewModel
import com.example.firstapp.ViewModel.StartViewModel
import com.example.firstapp.view.HomesScreens
import com.example.firstapp.viewmodel.HomesViewModels

private object Chemin {
    const val START = "start"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"

}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Chemin.START
    ) {

        // --- Écran d'accueil (Start) ---
        composable(Chemin.START) {
            val vm: StartViewModel = viewModel()
            StartScreen(
                vm = vm,
                onNavigateToLogin = { navController.navigate(Chemin.LOGIN) },
                onNavigateToRegister = { navController.navigate(Chemin.REGISTER) }
            )
        }

        // --- Écran de connexion ---
        composable(Chemin.LOGIN) {
            val vm: LoginViewModel = viewModel()
            LoginScreen(
                vm = vm,
                onLoginSuccess = {
                    // Après connexion -> Home, en vidant la pile (start/login/register)
                    navController.navigate(Chemin.HOME) {
                        popUpTo(Chemin.START) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Chemin.REGISTER) }
            )
        }

        // --- Écran d'inscription ---
        composable(Chemin.REGISTER) {
            val vm: RegisterViewModel = viewModel()
            RegisterScreen(
                vm = vm,
                onRegisterSuccess = {
                    // Après inscription -> Login (l'utilisateur doit ensuite se connecter)
                    navController.navigate(Chemin.LOGIN) {
                        popUpTo(Chemin.REGISTER) { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate(Chemin.LOGIN) }
            )
        }

        // --- Écran d'accueil après connexion (Home) ---
        composable(Chemin.HOME) {
//            val vm: HomesViewModels= viewModel()
//            HomesScreens(vm = vm)
            Dashboard()
        }
    }
}
