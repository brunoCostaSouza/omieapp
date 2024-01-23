package com.bruno.omieapp.presentation.compose

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun simpleToast(toastMessage: SharedFlow<Pair<Int?, String?>>) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        toastMessage
            .collect {
                var msg = ""
                if (it.first != null) {
                    msg = context.getText(it.first!!).toString()
                } else if (it.second != null) {
                    msg = it.second.toString()
                }
                Toast.makeText(
                    context,
                    msg,
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }
}