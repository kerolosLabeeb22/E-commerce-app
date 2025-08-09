package com.example.e_commerceapp.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.utils.AlertDialogContent
import com.example.e_commerceapp.utils.EcommerceButton
import com.example.e_commerceapp.utils.EcommerceTextField
import com.example.e_commerceapp.utils.LoadingDialog

@Composable
fun RegisterScreenContent(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()

) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = darkBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = com.example.e_commerceapp.R.drawable.route_logo),
            contentDescription = stringResource(
                com.example.e_commerceapp.R.string.app_logo
            ), modifier =
                modifier
                    .fillMaxWidth(0.7F)
                    .padding(top = 82.dp),
            contentScale = ContentScale.FillWidth
        )


        EcommerceTextField(
            state = viewModel.fullName,
            label = "Full Name",
            placeHolder = "enter your full name",
            errorState = viewModel.fullNameError.value,
        ) {

        }
        EcommerceTextField(
            state = viewModel.phoneNumber,
            label = "Mobile Number",
            placeHolder = "enter your mobile no.",
            errorState = viewModel.phoneNumberError.value,
        ) {

        }
        EcommerceTextField(
            state = viewModel.email,
            label = stringResource(com.example.e_commerceapp.R.string.email_address),
            placeHolder = stringResource(com.example.e_commerceapp.R.string.please_enter_your_email),
            errorState = viewModel.errorEmail.value,
        ) {

        }


        EcommerceTextField(
            state = viewModel.password,
            label = stringResource(com.example.e_commerceapp.R.string.password),
            placeHolder = stringResource(com.example.e_commerceapp.R.string.enter_your_password),
            errorState = viewModel.errorPassword.value
        ){

        }



        EcommerceButton(text = "Sign up", modifier = modifier.padding(top = 72.dp)) {
           viewModel.Register()
        }



        LoadingDialog(viewModel.isLoading)
        AlertDialogContent(viewModel.formErrorState)

    }
}

@Preview
@Composable
private fun RegisterScreenPrev() {
    RegisterScreenContent(rememberNavController())

}