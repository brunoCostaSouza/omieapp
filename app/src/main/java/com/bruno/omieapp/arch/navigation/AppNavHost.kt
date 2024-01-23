package com.bruno.omieapp.arch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bruno.omieapp.presentation.compose.MainScreen
import com.bruno.omieapp.presentation.compose.OrderForm
import com.bruno.omieapp.presentation.compose.SplashScreen
import com.bruno.omieapp.presentation.viewModel.MainViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    mViewModel: MainViewModel,
    startDestination: String = NavigationItem.SplashScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.SplashScreen.route) {
            SplashScreen(mViewModel)
        }
        composable(NavigationItem.Main.route) {
            MainScreen(mViewModel)
        }
        composable(NavigationItem.OrderForm.route) {
            OrderForm(navController)
        }
    }
}

