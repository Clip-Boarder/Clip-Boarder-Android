package com.clipboarder.clipboarder.ui.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Route for the main screen.
 */
const val MAIN_SCREEN = "main_screen"


/**
 * Main screen.
 *
 * As the main interface of the app, this screen presents a summary of the user's
 * uploaded file statistics, recently used files, and frequently accessed files.
 * It provides a quick overview of user activity and easy access to key functionalities.
 *
 * @param navController The navigation controller for navigating between screens.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun MainScreen(navController: NavController) {
    Text("Main Screen")
}