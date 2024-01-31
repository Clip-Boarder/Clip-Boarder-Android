package com.clipboarder.clipboarder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clipboarder.clipboarder.ui.screens.login.LOGIN_SCREEN
import com.clipboarder.clipboarder.ui.screens.login.LoginScreen
import com.clipboarder.clipboarder.ui.screens.main.MAIN_SCREEN
import com.clipboarder.clipboarder.ui.screens.main.MainScreen
import com.clipboarder.clipboarder.ui.screens.notice.NOTICE_SCREEN
import com.clipboarder.clipboarder.ui.screens.notice.NoticeScreen
import com.clipboarder.clipboarder.ui.screens.onboarding.ONBOARDING_SCREEN
import com.clipboarder.clipboarder.ui.screens.onboarding.OnboardingScreen
import com.clipboarder.clipboarder.ui.screens.storage.STORAGE_SCREEN
import com.clipboarder.clipboarder.ui.screens.storage.StorageScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ONBOARDING_SCREEN) {
        composable(ONBOARDING_SCREEN) { OnboardingScreen(navController = navController) }
        composable(LOGIN_SCREEN) { LoginScreen(navController = navController) }
        composable(MAIN_SCREEN) { MainScreen(navController = navController) }
        composable(NOTICE_SCREEN) { NoticeScreen(navController = navController) }
        composable(STORAGE_SCREEN) { StorageScreen(navController = navController) }
    }
}