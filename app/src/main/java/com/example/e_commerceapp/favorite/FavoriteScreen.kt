package com.example.e_commerceapp.favorite

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.data.Constant
import com.example.domain.entity.WishDataItemEntity
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.theme.blue
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.strokeColor
import com.example.e_commerceapp.ui.theme.white
import com.example.e_commerceapp.utils.TopAppBarContent

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val wishlist = viewModel.wishlist
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getWishlist()
    }

    Scaffold(topBar = {
        TopAppBarContent(
            navController = navController,
            searchQuery = "",
            onSearchChange = {
            }
        )
    }, contentColor = white) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .background(color = white)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(wishlist.size) { index ->
                    val item = wishlist[index]
                    FavoriteCardItem(
                        item = item,
                        onDeleteClick = { selectedItem ->
                            val productId = selectedItem.wishId ?: return@FavoriteCardItem
                            viewModel.deleteFromWishlist(
                                productId = productId,
                                onSuccess = {
                                    wishlist.remove(selectedItem)
                                },
                                onFailure = {

                                }
                            )
                        })
                }
            }
        }
    }
}

@Composable
fun FavoriteCardItem(
    modifier: Modifier = Modifier,
    item: WishDataItemEntity,
    onDeleteClick: (WishDataItemEntity) -> Unit,
    viewModel: FavoriteViewModel = viewModel()
) {
    val image = item.imageCover
    val productId = item.wishId ?: ""
    val brandName = item.brand?.name ?: ""
    val availableColor = item.availableColors
    val price = item.price ?: ""
    val title = item.title ?: ""
    val context = LocalContext.current
    var favoriteSelected by rememberSaveable { mutableStateOf(false) }


    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .height(150.dp)
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
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    color = darkBlue,
                    fontWeight = FontWeight.Bold,
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
                    text = "EGP ${price}",
                    color = darkBlue,
                    fontSize = 14.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        horizontal = 14.dp
                    )
                )


            }

            Column(
                modifier = modifier
                    .padding(end = 8.dp)
                    .fillMaxWidth()
            ) {

                Spacer(modifier = modifier.height(10.dp))
                Card(
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .padding(start = 80.dp),
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
                                    viewModel.addToWishlist(
                                        productId = productId.toString(),
                                        token = Constant.TOKEN
                                    )
                                } else {
                                    onDeleteClick(item)
                                }


                            },
                        tint = if (favoriteSelected) Color.Unspecified else blue
                    )


                }

                Spacer(modifier = modifier.height(50.dp))

                Box(
                    modifier = modifier
                        .background(
                            color = blue,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .height(40.dp)
                        .fillMaxWidth(),

                    ) {
                    Row(
                        modifier = modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Add to Cart",
                            color = white,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }


        }
    }
}


@Preview
@Composable
private fun FavoritePagePreview() {
    FavoriteScreen(navController = rememberNavController())
}