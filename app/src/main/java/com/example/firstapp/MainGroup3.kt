package com.example.firstapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.firstapp.View.AppScaffold
import com.example.firstapp.ui.theme.FirstAppTheme
import com.example.firstapp.View.ClientScreen
import com.example.firstapp.View.ProfilScreen
import com.example.firstapp.View.VehiculeScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    //AppNavigation()
                    //ClientScreen()
                    //VehiculeScreen()
                    //ProfilScreen ()
                    AppScaffold()
                }
            }
        }
    }
}