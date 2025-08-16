package com.example.e_commerceapp.categoryscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.SubCategoryDataItemEntity
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.white
import com.example.e_commerceapp.utils.SidebarMenu
import com.example.e_commerceapp.utils.TopAppBarContent

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val categoryList = viewModel.categoryList
    val subCategoryList = viewModel.subCategoryList

    var selectedCategory by remember { mutableStateOf<CategoryDataItemEntity?>(null) }

    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    val filteredSubCategory = if (searchQuery.isBlank()) {
        subCategoryList
    } else {
        subCategoryList.filter {
            it.name?.contains(searchQuery, ignoreCase = true) == true
        }
    }

    // Load categories once
    LaunchedEffect(Unit) {
        viewModel.getCategories()
    }

    LaunchedEffect(selectedCategory) {
        selectedCategory?.id?.let { id ->
            viewModel.getSubCategory(categoryId = id)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = white),
    ) {
        TopAppBarContent(
            navController = navController,
            searchQuery = searchQuery,
            onSearchChange = { searchQuery = it })

        Row(modifier = modifier.fillMaxSize()) {

            SidebarMenu(
                selectedCategory = selectedCategory?.name ?: "",
                categories = categoryList,
                onCategorySelected = {
                    selectedCategory = it
                }
            )

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = white)
            ) {
                SubCategoryContent(subCategories = filteredSubCategory)
            }
        }
    }

}

@Composable
fun SubCategoryContent(subCategories: List<SubCategoryDataItemEntity>) {
    if (subCategories.isEmpty()) {
        // ✅ عرض رسالة أو Placeholder لو القائمة فاضية
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "there is no sub categories",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = darkBlue
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(count = subCategories.size) { index ->
                val item = subCategories[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = white),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(8.dp)
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.name ?: "Unnamed",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = darkBlue,
                            textAlign = TextAlign.Center,
                            maxLines = 5
                        )
                    }
                }

            }
        }
    }

}