package com.example.e_commerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.destinations.LoginDestination
import com.example.e_commerceapp.destinations.RegisterDestination
import com.example.e_commerceapp.destinations.SplashDestination
import com.example.e_commerceapp.splash.SplashScreenContent
import com.example.e_commerceapp.ui.theme.EcommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    val navHostController = rememberNavController()
                    NavHost(navHostController , startDestination = SplashDestination){
                        composable<SplashDestination> {
                            SplashScreenContent(navHostController)
                        }

                        composable<LoginDestination> {

                        }

                        composable<RegisterDestination> {

                        }
                    }

                }
            }
        }
    }
}

