package com.bruno.omieapp.arch.navigation

enum class Screen {
    Home,
    SplashScreen,
    OrderForm,
}

sealed class NavigationItem(val route: String) {
    object SplashScreen : NavigationItem(Screen.SplashScreen.name)
    object Main : NavigationItem(Screen.Home.name)
    object OrderForm: NavigationItem(Screen.OrderForm.name)
}