package com.example.e_commerceapp.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.R
import com.example.e_commerceapp.ui.theme.black
import com.example.e_commerceapp.ui.theme.gray
import com.example.e_commerceapp.ui.theme.white

@Composable
fun EcommerceTextField(
    state: MutableState<String>,
    placeHolder: String,
    label: String,
    errorState: String? = null,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            label,
            color = white,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_medium)),
            modifier = modifier.align(Alignment.Start)
                .padding(start = 18.dp,top=42.dp,bottom=8.dp)
        )
        OutlinedTextField(
            value = state.value,
            onValueChange = {
                state.value = it
                onValueChange(it)
            },
             shape = RoundedCornerShape(14.dp),
            placeholder = {
                Text(placeHolder)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = black,
                unfocusedTextColor = black,
                focusedContainerColor = white,
                unfocusedContainerColor = white,
                focusedPlaceholderColor = gray,
                unfocusedPlaceholderColor = gray,
                focusedBorderColor = white,
                unfocusedBorderColor = white

            ), modifier = modifier.fillMaxWidth(0.9F)

        )
        if (errorState?.isNotEmpty() == true) {
            Text(
                text = errorState, color = Color.Red, fontSize = 14.sp,
                modifier = modifier
                    .padding(top = 8.dp, start = 17.dp)
                    .align(Alignment.Start)
            )

        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF004182, showSystemUi = true)
@Composable
private fun EcommerceTextFieldPrev() {

    val state = remember {
        mutableStateOf("")
    }
    EcommerceTextField(state, errorState = "null", placeHolder = "enter your name", label = "User Name") {

    }
}