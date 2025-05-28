package com.example.e_commerceapp.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.domain.R
import com.example.e_commerceapp.destinations.LoginDestination
import com.example.e_commerceapp.ui.theme.darkBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreenContent(navHostController: NavHostController,modifier: Modifier = Modifier) {
   LaunchedEffect(Unit) {
       delay(2000)
       navHostController.navigate(LoginDestination)
   }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = darkBlue)
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.SpaceBetween

    ) {
        Image(
            painter = painterResource(id = com.example.e_commerceapp.R.drawable.top_blur),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
            , contentScale = ContentScale.FillWidth
        )

        Image(
            painter = painterResource(id = com.example.e_commerceapp.R.drawable.route_logo),
            contentDescription = stringResource(com.example.e_commerceapp.R.string.app_logo),
            modifier = Modifier.fillMaxWidth(0.8F)
                , contentScale = ContentScale.FillWidth
        )

        Image(
            painter = painterResource(id = com.example.e_commerceapp.R.drawable.bottom_blur),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
            , contentScale = ContentScale.FillWidth
        )
    }


}

@Preview(showSystemUi = true)
@Composable
private fun SplashScreenContentPrev() {
    SplashScreenContent(rememberNavController())
}