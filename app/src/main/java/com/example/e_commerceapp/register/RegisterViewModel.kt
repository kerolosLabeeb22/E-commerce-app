package com.example.e_commerceapp.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Utils.ApiResult
import com.example.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    val email = mutableStateOf("")
    val errorEmail = mutableStateOf<String?>(null)
    val phoneNumber = mutableStateOf("")
    val phoneNumberError = mutableStateOf<String?>(null)
    val fullName = mutableStateOf("")
    val fullNameError = mutableStateOf<String?>(null)
    val password = mutableStateOf("")
    val errorPassword = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)
    val formErrorState = mutableStateOf<String?>(null)

    fun Register() {
        if (isFormedFieldValid()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    isLoading.value = true
                    val response = registerUseCase.invoke(
                        email.value,
                        password.value,
                        phoneNumber.value,
                        fullName.value
                    )
                    isLoading.value = false
                    when(response){
                        is ApiResult.Error -> {
                            formErrorState.value= response.exception.message
                        }
                        is ApiResult.Success -> {

                        }
                    }
                } catch (
                    e: Exception
                ) {
                    isLoading.value = false
                    Log.e("TAG", "login: ${e.message}")
                    formErrorState.value = e.message
                }

            }
        }
    }

    private fun isFormedFieldValid(): Boolean {
        if (email.value.isEmpty() || email.value.isBlank()) {
            errorPassword.value = "Required"

            return false
        } else {
            errorEmail.value = null
        }

        if (password.value.isEmpty() || password.value.isBlank()) {
            errorPassword.value = "Required"
            return false
        } else {
            errorPassword.value = null
        }
        if (phoneNumber.value.isEmpty() || phoneNumber.value.isBlank()) {
            phoneNumberError.value = "Required"
            return false
        } else {
            phoneNumberError.value = null
        }

        if (fullName.value.isEmpty() || fullName.value.isBlank()) {
            fullNameError.value = "Required"
            return false
        } else {
            fullNameError.value = null
        }
        return true
    }
}