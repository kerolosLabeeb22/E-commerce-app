package com.example.e_commerceapp.login

sealed interface LoginDirections {

    data object Initial : LoginDirections
    data object Register : LoginDirections
    data object Home : LoginDirections
    //data object Main : LoginDirections
}