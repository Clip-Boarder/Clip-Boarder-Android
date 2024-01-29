package com.clipboarder.clipboarder.ui.screens.notice

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Route for the notice screen.
 */
const val NOTICE_SCREEN = "notice_screen"


/**
 * Notice screen.
 *
 * This screen shows a list of announcements and notifications within the app,
 * such as content copying alerts and invitations. It serves as a central location
 * for users to stay informed about app updates and interactions.
 *
 * @param navController The navigation controller for navigating between screens.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun NoticeScreen(navController: NavController) {
    Text("Notice Screen")
}