package com.example.e_commerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.destinations.MainDestination
import com.example.e_commerceapp.destinations.LoginDestination
import com.example.e_commerceapp.destinations.RegisterDestination
import com.example.e_commerceapp.destinations.SplashDestination
import com.example.e_commerceapp.login.LoginScreenContent
import com.example.e_commerceapp.mainscreen.MainScreen
import com.example.e_commerceapp.register.RegisterScreenContent
import com.example.e_commerceapp.splash.SplashScreenContent
import com.example.e_commerceapp.ui.theme.EcommerceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navHostController = rememberNavController()
                    NavHost(navHostController, startDestination = SplashDestination) {
                        composable<SplashDestination> {
                            SplashScreenContent(navHostController)
                        }

                        composable<LoginDestination> {
                            LoginScreenContent(navHostController)
                        }

                        composable<RegisterDestination> {
                            RegisterScreenContent(navHostController)
                        }
                        composable<MainDestination> {
                            MainScreen(navController = navHostController)
                        }
                    }

                }
            }
        }
    }
}

