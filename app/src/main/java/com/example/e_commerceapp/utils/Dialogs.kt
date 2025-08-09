package com.example.e_commerceapp.utils

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.theme.darkBlue
import com.example.e_commerceapp.ui.theme.white

@Composable
fun LoadingDialog(
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    if (state.value) {
        Dialog(onDismissRequest = {
            state.value = false
        }
        ) {

            Box(
                modifier = modifier
                    .size(100.dp)
                    .background(color = white, RoundedCornerShape(14.dp)),

                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = darkBlue)
            }
        }
    }
}

@Preview
@Composable
private fun LoadingDialogPrev() {
    LoadingDialog(state = remember { mutableStateOf(true) })
}

@Composable
fun AlertDialogContent(errorState: MutableState<String?>, modifier: Modifier = Modifier) {
    if (errorState.value?.isNotEmpty() == true) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                errorState.value = ""
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        errorState.value = ""
                    }
                ) {
                    Text(stringResource(R.string.ok), fontSize = 18.sp, color = darkBlue)
                }
            },

            text = { Text(errorState.value ?: "", fontSize = 16.sp) }

        )
    }

}

@Preview
@Composable
private fun AlertDialogPrev() {
    AlertDialogContent(remember { mutableStateOf("is not valid") })
}