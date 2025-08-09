package com.example.e_commerceapp.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_commerceapp.destinations.MainDestination
import com.example.e_commerceapp.destinations.LoginDestination
import com.example.e_commerceapp.destinations.RegisterDestination
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.white
import com.example.e_commerceapp.utils.AlertDialogContent
import com.example.e_commerceapp.utils.EcommerceButton
import com.example.e_commerceapp.utils.EcommerceTextField
import com.example.e_commerceapp.utils.LoadingDialog

@Composable
fun LoginScreenContent(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()

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

        Text(
            text = stringResource(com.example.e_commerceapp.R.string.welcome_back_to_route),
            color = white,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_semi_bold)),
            modifier = modifier
                .padding(top = 16.dp)
                .align(Alignment.Start)
                .padding(horizontal = 8.dp)
        )

        Text(
            text = stringResource(com.example.e_commerceapp.R.string.please_sign_in_with_your_mail),
            color = white,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_light)),
            modifier = modifier
                .align(Alignment.Start)
                .padding(horizontal = 8.dp)
        )
        EcommerceTextField(
            state = viewModel.email,
            label = stringResource(com.example.e_commerceapp.R.string.email_address),
            placeHolder = stringResource(com.example.e_commerceapp.R.string.please_enter_your_email),
            errorState = viewModel.emailError.value,
        ) {

        }


        EcommerceTextField(
            state = viewModel.password,
            label = stringResource(com.example.e_commerceapp.R.string.password),
            placeHolder = stringResource(com.example.e_commerceapp.R.string.enter_your_password),
            errorState = viewModel.passwordError.value
        ) {

        }

        Text(
            text = stringResource(com.example.e_commerceapp.R.string.forgot_password),
            color = white,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_regular)),
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 22.dp, top = 8.dp)
        )

        EcommerceButton(text = "Login", modifier = modifier.padding(top = 72.dp)) {
            viewModel.login()

        }

        Row(
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 32.dp, top = 14.dp)
        ) {
            Text(
                text = "Don’t have an account?",
                color = white,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_medium)),
            )
            Text(
                text = " Create Account",
                color = white,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_medium)),
                modifier = modifier.clickable {
                    viewModel.navigateToRegister()
                }
            )
        }

        LoadingDialog(viewModel.isLoading)

        AlertDialogContent(viewModel.formErrorState)
        ObserveEvent(viewModel, navHostController)

    }
}

@Composable
fun ObserveEvent(
    viewModel: LoginViewModel,
    navHostController: NavHostController,

) {
    when(viewModel.navigator.value){
        LoginDirections.Home -> {
            navHostController.navigate(MainDestination){
                popUpTo(LoginDestination){
                    inclusive = true
                }
            }
        }
        LoginDirections.Initial -> {

        }
        LoginDirections.Register -> {
            navHostController.navigate(RegisterDestination){
                viewModel.navigator.value= LoginDirections.Initial
            }
        }

//        LoginDirections.Main -> {
//            navHostController.navigate(MainDestination){
//                popUpTo(LoginDestination){
//                    inclusive = true
//                }
//            }
//        }
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreenContent(rememberNavController())

}