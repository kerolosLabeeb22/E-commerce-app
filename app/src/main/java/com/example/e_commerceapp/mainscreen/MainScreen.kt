package com.example.e_commerceapp.mainscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.categoryscreen.CategoryScreen
import com.example.e_commerceapp.homescreen.HomeScreenContent
import com.example.e_commerceapp.ui.theme.blue
import com.example.e_commerceapp.ui.theme.white

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val list = listOf(
        BottomItem(
            title = "home",
            selectedIcon = painterResource(id = R.drawable.ic_home),
        ),
        BottomItem(
            title = "brand",
            selectedIcon = painterResource(id = R.drawable.ic_brand),
        ),
        BottomItem(
            title = "favorite",
            selectedIcon = painterResource(id = R.drawable.ic_favorite),
        ),
        BottomItem(
            title = "profile",
            selectedIcon = painterResource(id = R.drawable.ic_person),
        )


    )

    var selectedIndexItem by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            Box(modifier = modifier.clip(RoundedCornerShape(16.dp))) {
                BottomNavigation(backgroundColor = blue) {
                    list.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            selected = selectedIndexItem == index,

                            onClick = {
                                selectedIndexItem = index
                            },
                            icon = {
                                Box(
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .background(color = if (index == selectedIndexItem) white else Color.Transparent)
                                ) {
                                    Icon(
                                        painter = item.selectedIcon,
                                        contentDescription = item.title,
                                        tint = if (index == selectedIndexItem) blue else white
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        ScreenContent(
            modifier = modifier.padding(innerPadding),
            selectedIndex = selectedIndexItem,
            navController = navController
        )
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavHostController
) {
    when (selectedIndex) {
        0 -> HomeScreenContent(navController = navController)
        1 -> CategoryScreen(navController = navController)
    }
}

data class BottomItem(
    val title: String,
    val selectedIcon: Painter
)