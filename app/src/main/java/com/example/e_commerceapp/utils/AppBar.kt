package com.example.e_commerceapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.destinations.CartDestination
import com.example.e_commerceapp.ui.theme.blue
import com.example.e_commerceapp.ui.theme.white

@Composable
fun TopAppBarContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    searchQuery: String = "",
    onSearchChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.white))
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {

        Image(painter = painterResource(R.drawable.top_logo), contentDescription = "logo")

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

            OutlinedTextField(
                value = searchQuery, onValueChange = {
                    onSearchChange(it)
                },
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = white,
                    unfocusedContainerColor = white,
                    focusedIndicatorColor = blue,
                    unfocusedIndicatorColor = blue

                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.icon__search),
                        contentDescription = "search",
                        modifier = modifier.padding(start = 18.dp)
                    )
                }, modifier = modifier
                    .weight(0.8f)
                    .height(50.dp)


            )

            IconButton(
                onClick = {
                    navController.navigate(CartDestination)
                },

                ) {
                Icon(
                    painter = painterResource(R.drawable.icon__shopping_cart),
                    contentDescription = "cart",
                    modifier = modifier
                        .weight(0.1f)
                        .padding(top = 4.dp),
                    tint = blue
                )
            }


        }

    }
}