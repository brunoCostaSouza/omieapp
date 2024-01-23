package com.bruno.omieapp.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bruno.omieapp.R
import com.bruno.omieapp.presentation.theme.OmieAppTheme
import com.bruno.omieapp.presentation.viewModel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    mViewModel: MainViewModel
) {
    OmieAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(R.string.msg_splash)
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_android_24),
                    contentDescription = null
                )
            }
        }
        LaunchedEffect(key1 = 1, block = {
            delay(1100)
            mViewModel.getAllOrders()
        })
    }
}