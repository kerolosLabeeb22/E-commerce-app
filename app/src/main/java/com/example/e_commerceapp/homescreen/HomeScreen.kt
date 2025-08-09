package com.example.e_commerceapp.homescreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.slidermodel.SliderModel
import com.example.e_commerceapp.ui.theme.white
import com.example.e_commerceapp.utils.Banners
import com.example.e_commerceapp.utils.TopAppBarContent


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val bannerViewModel: BannerViewModel = hiltViewModel()
    val banners = remember { mutableStateListOf<SliderModel>() }

    var searchQuery by rememberSaveable { mutableStateOf("") }

    var showBannerLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        bannerViewModel.loadBanner().observeForever {
            Log.e("TAG", "Banners received: $it")
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBarContent(
                navController = navController,
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it })
        },
        containerColor = white
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            item {
                if (showBannerLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Banners(banners)
                }
            }
        }
    }
}