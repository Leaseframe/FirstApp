package com.example.firstapp.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
import com.example.firstapp.ViewModel.HomeViewModel
import com.example.firstapp.ViewModel.LoginViewModel
import com.example.firstapp.ViewModel.RegisterViewModel
import com.example.firstapp.ViewModel.StartViewModel
=======
import com.example.firstapp.viewmodel.HomeViewModel
import com.example.firstapp.viewmodel.StartViewModel
>>>>>>> origin/group2

private object Routes {
    const val START = "start"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
<<<<<<< HEAD
        navController = navController,
        startDestination = Routes.START
=======
        navController = nC,
        startDestination = "home",
>>>>>>> origin/group2
    ) {

        // --- Écran d'accueil (Start) ---
        composable(Routes.START) {
            val vm: StartViewModel = viewModel()
            StartScreen(
                vm = vm,
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) },
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) }
            )
        }
<<<<<<< HEAD

        // --- Écran de connexion ---
        composable(Routes.LOGIN) {
            val vm: LoginViewModel = viewModel()
            LoginScreen(
                vm = vm,
                onLoginSuccess = {
                    // Après connexion -> Home, en vidant la pile (start/login/register)
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.START) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        // --- Écran d'inscription ---
        composable(Routes.REGISTER) {
            val vm: RegisterViewModel = viewModel()
            RegisterScreen(
                vm = vm,
                onRegisterSuccess = {
                    // Après inscription -> Login (l'utilisateur doit ensuite se connecter)
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) }
            )
        }

        // --- Écran d'accueil après connexion (Home) ---
        composable(Routes.HOME) {
            val vm: HomeViewModel = viewModel()
            HomeScreen(vm = vm)
        }
=======
        composable("home"){
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = viewModel
            )
        }
>>>>>>> origin/group2
    }
}
