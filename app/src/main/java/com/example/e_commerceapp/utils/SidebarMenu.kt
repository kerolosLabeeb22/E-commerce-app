package com.example.e_commerceapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.entity.CategoryDataItemEntity
import com.example.e_commerceapp.ui.theme.blue
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.sideBarBg
import com.example.e_commerceapp.ui.theme.strokeColor
import com.example.e_commerceapp.ui.theme.white
import kotlin.collections.forEach

@Composable
fun SidebarMenu(
    selectedCategory: String,
    categories: List<CategoryDataItemEntity>,
    onCategorySelected: (CategoryDataItemEntity) -> Unit
) {


    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(150.dp)
            .padding(16.dp)
            .border(1.dp, strokeColor, RoundedCornerShape(4.dp))
            .background(color = sideBarBg)
            .scrollable(scrollState, enabled = true, orientation = Orientation.Vertical)
    ) {
        categories.forEach { category ->
            val isSelected = selectedCategory == category.name

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable { onCategorySelected(category) }
                    .background(if (isSelected) white else Color.Transparent),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .background(if (isSelected) blue else Color.Transparent)
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {

                    Text(
                        text = category.name ?: "",
                        color = if (isSelected) blue else darkBlue,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }

}