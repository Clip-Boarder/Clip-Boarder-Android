package com.clipboarder.clipboarder.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.clipboarder.clipboarder.ui.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity
 *
 * This Activity is the first to be called when the application starts.
 * It sets up the UI using Jetpack Compose and uses the AppScreen Composable function as the root view.
 *
 * @author YoungJin Sohn
 * @since v1.0.0
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

/**
 * AppScreen
 *
 * This Composable function creates the navigation controller and manages screen transitions
 * through AppNavigation. All screens are displayed according to the navigation rules
 * defined within this Composable.
 *
 * @author YoungJin Sohn
 * @since v1.0.0
 * @see AppNavigation
 */
@Composable
fun AppScreen() {
    MaterialTheme{
        AppNavigation()
    }
}