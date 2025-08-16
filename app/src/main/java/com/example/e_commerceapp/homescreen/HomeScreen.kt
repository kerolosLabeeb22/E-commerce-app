package com.example.e_commerceapp.homescreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.data.Constant
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.ProductDataItemEntity
import com.example.domain.slidermodel.SliderModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.theme.blue
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.discountColor
import com.example.e_commerceapp.ui.theme.strokeColor
import com.example.e_commerceapp.ui.theme.white
import com.example.e_commerceapp.utils.Banners
import com.example.e_commerceapp.utils.TopAppBarContent


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val bannerViewModel: BannerViewModel = hiltViewModel()
    val banners = remember { mutableStateListOf<SliderModel>() }

    val categoryList = viewModel.categoryList
    val productList = viewModel.productList
    val electronicProductList = viewModel.electronicProductList
    val newArrivalProductList = viewModel.newArrivalProductList

    var searchQuery by rememberSaveable { mutableStateOf("") }

    var showBannerLoading by remember { mutableStateOf(true) }


    val filteredProducts = if (searchQuery.isBlank()) {
        productList
    } else {
        productList.filter {
            it.title?.contains(searchQuery, ignoreCase = true) == true
        }
    }

    LaunchedEffect(Unit) {
        bannerViewModel.loadBanner().observeForever {
            Log.e("TAG", "Banners received: $it")
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getCategory()
    }

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    LaunchedEffect(Unit) {
        viewModel.getElectronicProduct()
    }

    LaunchedEffect(Unit) {
        viewModel.getNewArrivalProduct()
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

            item {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "Categories",
                        color = darkBlue,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        modifier = modifier.weight(1f)
                    )

                    Text(
                        text = "view all",
                        color = darkBlue,
                        fontSize = 18.sp,

                        )

                }
            }

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
                    CategoryContent(categoryList)
                }
            }
            item {
                Text(
                    text = "Products",
                    color = darkBlue,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 10.dp)
                )
            }
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
                    ProductContent(filteredProducts)
                }
            }

            item {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .height(120.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.footer_banner),
                        contentDescription = null,
                        modifier = modifier
                            .size(500.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

            }
            item {
                Text(
                    text = "Electronics",
                    color = darkBlue,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 10.dp)
                )
            }
            item {
                ProductContent(electronicProductList)
            }
            item {
                Text(
                    text = "New Arrival",
                    color = darkBlue,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 10.dp)
                )
            }
            item {
                ProductContent(newArrivalProductList)
            }
        }
    }
}

@Composable
fun CategoryContent(category: List<CategoryDataItemEntity>) {
    val firstRowState = rememberLazyListState()
    val secondRowState = rememberLazyListState()
    val firstRow = category.filterIndexed { index, _ -> index % 2 == 0 }
    val secondRow = category.filterIndexed { index, _ -> index % 2 == 1 }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        LazyRow(
            state = firstRowState,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(firstRow) { category ->
                CategoryItem(category)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            state = secondRowState,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(secondRow) { category ->
                CategoryItem(category)
            }
        }

    }


}

@Composable
fun CategoryItem(categoryItem: CategoryDataItemEntity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()

    ) {
        AsyncImage(
            model = categoryItem.image,
            contentDescription = categoryItem.name,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .border(1.dp, strokeColor, CircleShape),

            contentScale = ContentScale.Crop,

            )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = categoryItem.name ?: "",
            modifier = Modifier
                .width(90.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            color = darkBlue,
            fontWeight = FontWeight.SemiBold,

            )
    }
}

@Composable
fun ProductContent(product: List<ProductDataItemEntity>) {
    LazyRow {
        items(product.size) {
            ProductItem(product[it])
        }
    }
}

@Composable
fun ProductItem(
    productItem: ProductDataItemEntity,
    viewModel: HomeViewModel = viewModel()
) {
    var favoriteSelected by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val token = Constant.TOKEN
    val productId = productItem.id
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(270.dp)
            .border(1.dp, strokeColor, RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = white)
    ) {
        Column(

            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = productItem.imageCover,
                    contentDescription = productItem.title,
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            1.dp,
                            strokeColor,
                            RoundedCornerShape(10.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                Card(
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = white)
                ) {
                    Icon(
                        painter = if (favoriteSelected) painterResource(R.drawable.selected_fav_ic) else painterResource(
                            R.drawable.ic_favorite
                        ),
                        contentDescription = "favorite icon",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                favoriteSelected = !favoriteSelected
                                Toast.makeText(
                                    context,
                                    if (favoriteSelected) "Added to favorites" else "Removed from favorites",
                                    Toast.LENGTH_SHORT
                                ).show()

                                if (favoriteSelected) {
                                    viewModel.addToWishlist(productId.toString(), token)
                                    favoriteSelected = true
                                } else {
                                    viewModel.removeFromWishlist(productId.toString(), token)
                                }

                            },
                        tint = if (favoriteSelected) Color.Unspecified else blue
                    )


                }

            }

            Text(
                text = productItem.title ?: "", color = darkBlue, fontSize = 16.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 14.dp)

            )
            Text(
                text = productItem.description ?: "",
                color = darkBlue,
                fontSize = 14.sp,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 14.dp),
                overflow = TextOverflow.Ellipsis
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "EGP ${productItem.priceAfterDiscount} ",
                    color = darkBlue,
                    fontSize = 14.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )

                Text(
                    text = "${productItem.price} EGP",
                    color = discountColor,
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    textDecoration = TextDecoration.LineThrough
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Review (${productItem.ratingsAverage})",
                    fontSize = 12.sp,
                    color = darkBlue,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = "star icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = {
                    val token = Constant.TOKEN
                    val productId = productItem.id

                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()

                    viewModel.addToCart(productId = productId.toString(), token = token)

                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = "plus icon",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }

}