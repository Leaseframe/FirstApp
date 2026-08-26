package com.example.firstapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
<<<<<<< HEAD
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapp.view.AppNavigation
=======
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapp.View.AppNavigation
import com.example.firstapp.View.NewDeliveryScreen
import com.example.firstapp.View.TripDetailScreen
>>>>>>> origin/groupe4
import com.example.firstapp.ui.theme.FirstAppTheme
import com.example.firstapp.view.HomeScreen
=======
import com.example.firstapp.View.AppScaffold
import com.example.firstapp.ui.theme.FirstAppTheme
import com.example.firstapp.View.ClientScreen
import com.example.firstapp.View.ProfilScreen
import com.example.firstapp.View.VehiculeScreen
>>>>>>> origin/group3

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
<<<<<<< HEAD
                    //AppNavigation()
<<<<<<< HEAD
                    HomeScreen()
=======
                    //ClientScreen()
                    //VehiculeScreen()
                    //ProfilScreen ()
                    AppScaffold()
>>>>>>> origin/group3
=======
                    Surface(color = Color(0xFF20201F)) {
                        NewDeliveryScreen(
                            onBack = { finish() }
                        )
                        /*TripDetailScreen(
                            onBack = { finish() }
                        )*/
                    }
>>>>>>> origin/groupe4
                }
            }
        }
    }
}