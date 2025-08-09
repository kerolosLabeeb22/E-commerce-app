package com.example.e_commerceapp.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Utils.ApiResult
import com.example.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    val email = mutableStateOf("")
    val emailError = mutableStateOf<String?>(null)
    val password = mutableStateOf("")
    val passwordError = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)
    val formErrorState = mutableStateOf<String?>(null)
    val navigator = mutableStateOf<LoginDirections>(LoginDirections.Initial)
    fun login() {
        if (isFormFieldsValid())
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    isLoading.value = true
                    val response = loginUseCase.invoke(email.value, password.value)
                    isLoading.value = false
                    when (response) {
                        is ApiResult.Error -> {
                            formErrorState.value = response.exception.message
                        }

                        is ApiResult.Success -> {
                            // Clear Backstack
                            // Navigate to Home
                            navigator.value = LoginDirections.Home
                        }
                    }
                    // Success or Error
                }
            } catch (e: Exception) {
                isLoading.value = false
                Log.e("TAG", "login: ${e.message}")
                formErrorState.value = e.message
            }
    }

    fun navigateToRegister() {
        navigator.value = LoginDirections.Register
    }

    private fun isFormFieldsValid(): Boolean {
        if (email.value.isEmpty() || email.value.isBlank()) {
            emailError.value = "Required"
            return false
        } else {
            emailError.value = null
        }

        if (password.value.isEmpty() || password.value.isBlank()) {
            passwordError.value = "Required"
            return false
        } else {
            passwordError.value = null
        }


        return true
    }
}

//if (email.value.isEmpty() || email.value.isBlank()) {
//    emailError.value = "Required"
//    return false
//} else {
//    emailError.value = null
//}
//if (password.value.isEmpty() || password.value.isBlank()) {
//    passwordError.value = "Required"
//    return false
//} else {
//    passwordError.value = null
//}
//
//return true