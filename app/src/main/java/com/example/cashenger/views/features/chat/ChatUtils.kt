package com.example.cashenger.views.features.chat

import androidx.navigation.NavHostController
import com.example.cashenger.views.features.screens.Screens

fun getChatScreenNavigation(navHostController: NavHostController) = FromChatToDestination(
    navigateToRecords = {
        navHostController.navigate(Screens.ListScreen.route.replace("{navigateFor}", it))
    }
)