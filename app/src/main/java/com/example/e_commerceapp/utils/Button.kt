package com.example.e_commerceapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.R
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.white

@Composable
fun EcommerceButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(14.dp),
        modifier = modifier.fillMaxWidth(0.9F),
        colors = ButtonDefaults.buttonColors(
            containerColor = white,
            contentColor = darkBlue

        )
    ) {
        Text(
            text,
            color = darkBlue,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(com.example.e_commerceapp.R.font.poppins_semi_bold))
        )
    }

}

@Preview(showBackground = true , backgroundColor = 0xFF004182)
@Composable
private fun EcommerceButtonPrev() {
    EcommerceButton(text = "login") {

    }
}