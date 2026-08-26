package com.example.firstapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.firstapp.ui.theme.FirstAppTheme

class Groupe4 : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Surface(color = Color(0xFF20201F)) {
//                        NewDeliveryScreen(
//                            //onBack = { finish() }
//                        )
//                        TripDetailScreen(
//                          //  onBack = { finish() }
//                        )
                    }
                }
            }
        }
    }
}