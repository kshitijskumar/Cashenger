package com.example.cashenger.views.features.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cashenger.views.features.EditTransactionScreen
import com.example.cashenger.views.features.chat.ChatScreen
import com.example.cashenger.views.features.chat.getChatScreenNavigation
import com.example.cashenger.views.features.records.RecordsListScreen
import com.example.cashenger.views.features.records.getRecordsScreenNavigation
import com.example.cashenger.views.features.screens.Screens

@ExperimentalFoundationApi
@Composable
fun CashengerNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.ChatScreen.route) {
        composable(
            route = Screens.ChatScreen.route,
            arguments = listOf(navArgument("navigateFor"){ type = NavType.StringType })
        ) {
            ChatScreen(getChatScreenNavigation(navController))
        }
        composable(route = Screens.ListScreen.route) {
            RecordsListScreen(
                navigatingFor = it.arguments?.getString("navigateFor") ?: "all",
                fromRecordsToDestination = getRecordsScreenNavigation(navController)
            )
        }
        composable(
            route = Screens.EditTransactionScreen.route,
            arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
        ) {
            EditTransactionScreen(
                transactionId = it.arguments?.getInt("transactionId") ?: 0
            )
        }
    }
}