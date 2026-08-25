package com.example.firstapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapp.View.AppNavigation
import com.example.firstapp.View.NewDeliveryScreen
import com.example.firstapp.View.TripDetailScreen
import com.example.firstapp.ui.theme.FirstAppTheme

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
                    Surface(color = Color(0xFF20201F)) {
                        NewDeliveryScreen(
                            onBack = { finish() }
                        )
                        /*TripDetailScreen(
                            onBack = { finish() }
                        )*/
                    }
                }
            }
        }
    }
}