package com.bruno.omieapp.presentation.compose

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun ToolBarApp() {
    TopAppBar(
        title = {
            Text(text = "Home")
        }
    )
}