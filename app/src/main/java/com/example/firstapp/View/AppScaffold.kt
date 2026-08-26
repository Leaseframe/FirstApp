package com.example.firstapp.View

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.ViewModel.NewDeliveryViewModel
import com.example.firstapp.ViewModel.TripDetailViewModel
import com.example.firstapp.view.HomesScreens

// import com.example.logistique.ui.home.HomeScreen // <- adapte le chemin réel fourni par l'autre groupe

private object Routes {
    const val HOME = "home"
    const val CLIENT = "client"
    const val VEHICULE = "vehicule"
    const val PROFIL = "profil"
}

private data class BottomNavItem(val route: String, val label: String, val icon: ImageVector)

private val bottomNavItems = listOf(
    BottomNavItem(Routes.HOME, "Accueil", Icons.Filled.Home),
    BottomNavItem(Routes.CLIENT, "Clients", Icons.Filled.People),
    BottomNavItem(Routes.VEHICULE, "Véhicules", Icons.Filled.LocalShipping),
    BottomNavItem(Routes.PROFIL, "Profil", Icons.Filled.Person)
)


private fun titrePour(route: String?): String? = when (route) {
    Routes.CLIENT -> "Clients"
    Routes.VEHICULE -> "Véhicules"
    Routes.PROFIL -> "Profil"
    else -> null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            val titre = titrePour(currentRoute)
            if (titre != null) {
                TopAppBar(
                    title = { Text(titre) },
                    navigationIcon = {
                        IconButton(onClick =  { navController.popBackStack()}) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        },
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val estSelectionne = backStackEntry?.destination
                        ?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        selected = estSelectionne,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomesScreens()  // écran de l'autre groupe, appelé sans modification
            }
            composable(Routes.CLIENT) {
                ClientScreen()
            }
            composable(Routes.VEHICULE) {
                VehiculeScreen()
            }
            composable(Routes.PROFIL) {
                val nC = rememberNavController()
                ProfilScreen(navController = nC)
            }
            composable("new_delivery") {
                val vm: NewDeliveryViewModel = viewModel()
                NewDeliveryScreen(viewModel = vm)
            }

            composable("trip_details") {
                val vm: TripDetailViewModel = viewModel()
                TripDetailScreen(viewModel = vm)
            }
        }
    }
}
