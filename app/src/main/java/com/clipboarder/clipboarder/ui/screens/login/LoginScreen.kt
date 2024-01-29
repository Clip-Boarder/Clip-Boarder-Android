package com.clipboarder.clipboarder.ui.screens.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Route for the login screen.
 */
const val LOGIN_SCREEN = "login_screen"

/**
 * Login screen.
 *
 * This screen facilitates user login, featuring input fields for entering
 * credentials and options for authentication. It's the gateway for users
 * to access their personal Clipboarder accounts and content.
 *
 * @param navController The navigation controller for navigating between screens.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun LoginScreen(navController: NavController) {
    Text("Login Screen")
}