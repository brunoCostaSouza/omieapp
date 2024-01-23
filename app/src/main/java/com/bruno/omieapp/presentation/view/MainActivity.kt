package com.bruno.omieapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bruno.omieapp.arch.navigation.AppNavHost
import com.bruno.omieapp.arch.navigation.NavigationItem
import com.bruno.omieapp.presentation.theme.OmieAppTheme
import com.bruno.omieapp.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val mViewModel: MainViewModel by viewModel()
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OmieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    navHostController = rememberNavController()
                    AppNavHost(
                        navController = navHostController,
                        mViewModel = mViewModel
                    )
                }
            }
        }
        setupLiveData()
    }

    private fun setupLiveData() {
        mViewModel.goToMainScreenLiveDate.observe(this) {
            navHostController.navigate(NavigationItem.Main.route)
        }
        mViewModel.goToOrderFormLiveDate.observe(this) {
            navHostController.navigate(NavigationItem.OrderForm.route)
        }
    }
}