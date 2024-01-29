package com.clipboarder.clipboarder.ui.screens.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Route for the onboarding screen.
 */
const val ONBOARDING_SCREEN = "onboarding_screen"

/**
 * Onboarding screen.
 *
 * This screen is displayed only when the user launches the app for the first time.
 * It provides an overview of Clipboarder's functionality, demonstrating how to use
 * the app and highlighting its key features.
 *
 * @param navController The navigation controller for navigating between screens.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun OnBoardingScreen(navController: NavController) {
    Text("Onboarding Screen")
}