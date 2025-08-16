package com.example.home_screen.cart

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.data.models.cart.CartProductsItem
import com.example.domain.entity.CartProductsItemEntity
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.theme.blue
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.strokeColor
import com.example.e_commerceapp.ui.theme.totalPriceColor
import com.example.e_commerceapp.ui.theme.white
import com.example.e_commerceapp.utils.TopAppBarContent

@Composable
fun CartScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val products = viewModel.products

    var totalPrice by remember { mutableStateOf(0) }

    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    val filteredProducts  = if (searchQuery.isBlank()) {
        products
    } else {
        products.filter {
            it.product?.title?.contains(searchQuery, ignoreCase = true) == true
        }
    }

    // تحميل البيانات عند الدخول للشاشة
    LaunchedEffect(Unit) {
        viewModel.getCartProducts()
    }

    Scaffold(topBar = {
        TopAppBarContent(
            navController = navController,
            searchQuery = searchQuery,
            onSearchChange = {
                searchQuery = it
            }
        )
    }) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = white)
                .padding(innerPadding)
        ) {

            LazyColumn(modifier = modifier.weight(1f)) {
                items(filteredProducts.size) { index ->
                    val item = filteredProducts[index]
                    CartItemCard(
                        item = item,
                        onDeleteClick = { selectedItem ->
                            val productId = selectedItem.product?.CartId ?: return@CartItemCard

                            viewModel.removeFromCart(
                                productId = productId,
                                onSuccess = {
                                    products.remove(selectedItem)
                                    totalPrice = it.cartData?.totalCartPrice ?: 0
                                },
                                onFailure = {
                                    Log.e("CartScreen", "Error: $it")
                                }
                            )
                        })
                }
            }

            Row(
                modifier = modifier
                    .weight(0.2f)
                    .background(color = white)
                    .padding(vertical = 16.dp, horizontal = 26.dp)
                    .fillMaxSize()
            ) {

                Column() {
                    Text(
                        text = "Total Price",
                        fontSize = 18.sp,
                        color = totalPriceColor,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$totalPrice EGP",
                        fontSize = 18.sp,
                        color = darkBlue,
                        fontWeight = FontWeight.Bold
                    )

                }
                Spacer(modifier = modifier.width(12.dp))

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = blue, shape = RoundedCornerShape(20.dp))
                        .clickable {

                        }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Checkout",
                            fontSize = 18.sp,
                            color = white,
                            fontWeight = FontWeight.Bold,
//                            modifier = modifier.padding(vertical = 16.dp, horizontal = 26.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.right_arrow_ic),
                            contentDescription = null,
                            tint = white
                        )
                    }
                }


            }

        }

    }
}

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    item: CartProductsItemEntity,
    onDeleteClick: (CartProductsItemEntity) -> Unit
) {
    val product = item.product ?: return
    val image = product.imageCover ?: ""
    val brandName = product.cartBrand?.name ?: "no brand"
    val price = item.price
    val title = product.title ?: ""

    var count by remember { mutableStateOf(0) }
    val productList = remember { mutableStateListOf<CartProductsItem>() }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .border(1.dp, strokeColor, RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(containerColor = white),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(modifier = modifier.fillMaxSize()) {

            Box(modifier = modifier.fillMaxHeight()) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = modifier
                        .width(120.dp)
                        .border(1.dp, strokeColor, RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = modifier
                    .padding(top = 16.dp, start = 12.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = darkBlue,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .width(160.dp)
                        .height(30.dp)
                )

                Text(
                    text = brandName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkBlue
                )

                Text(
                    text = "EGP $price",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkBlue,
                    modifier = modifier.padding(top = 12.dp)
                )
            }

            Column(modifier = modifier.padding(end = 8.dp)) {
                Spacer(modifier = modifier.height(10.dp))
                IconButton(onClick = {
                    onDeleteClick
                }, modifier = modifier.padding(start = 50.dp)) {

                    IconButton(onClick = {
                        onDeleteClick(item)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.delete_ic),
                            contentDescription = null,
                            tint = darkBlue,
                        )
                    }

                }
                Spacer(modifier = modifier.height(20.dp))
                Box(
                    modifier = modifier
                        .background(
                            color = blue,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .height(40.dp)


                ) {
                    Row(
                        modifier = modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (count > 0) {
                                count--
                            }
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.minus_ic),
                                contentDescription = null,
                                tint = white
                            )
                        }

                        Text(
                            text = count.toString(),
                            color = white,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        IconButton(onClick = {
                            count++
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.add_ic),
                                contentDescription = null,
                                tint = white
                            )
                        }
                    }


                }


            }


        }
    }


}




