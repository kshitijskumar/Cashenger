package com.example.cashenger.views.features.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cashenger.views.features.chat.ChatScreen
import com.example.cashenger.views.features.chat.getChatScreenNavigation
import com.example.cashenger.views.features.records.RecordsListScreen
import com.example.cashenger.views.features.screens.Screens

@ExperimentalFoundationApi
@Composable
fun CashengerNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.ChatScreen.route) {
        composable(
            route = Screens.ChatScreen.route,
            arguments = listOf(navArgument("{navigateFor}"){ type = NavType.StringType })
        ) {
            ChatScreen(getChatScreenNavigation(navController))
        }
        composable(route = Screens.ListScreen.route) {
            RecordsListScreen()
        }
    }
}