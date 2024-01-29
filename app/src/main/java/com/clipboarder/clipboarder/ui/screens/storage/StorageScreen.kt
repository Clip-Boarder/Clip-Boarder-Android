package com.clipboarder.clipboarder.ui.screens.storage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Route for the storage screen.
 */
const val STORAGE_SCREEN = "storage_screen"


/**
 * Storage screen.
 *
 * Users can view, manage, and delete the clipboard content they have uploaded
 * to Clipboarder. This screen offers an organized view of all stored items,
 * enhancing content management and accessibility.
 *
 * @param navController The navigation controller for navigating between screens.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun StorageScreen(navController: NavController) {
    Text("Storage Screen")
}