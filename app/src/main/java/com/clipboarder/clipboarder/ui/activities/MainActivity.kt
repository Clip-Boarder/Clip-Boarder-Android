package com.clipboarder.clipboarder.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.clipboarder.clipboarder.R
import com.clipboarder.clipboarder.ui.navigation.AppNavigation

/**
 * MainActivity
 *
 * This Activity is the first to be called when the application starts.
 * It sets up the UI using Jetpack Compose and uses the AppScreen Composable function as the root view.
 *
 * @author YoungJin Sohn
 * @since v1.0.0
 */
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
    val navController = rememberNavController()
    val fontFamily = FontFamily(
        Font(R.font.pretendard_thin, FontWeight.Thin),
        Font(R.font.pretendard_extralight, FontWeight.ExtraLight),
        Font(R.font.pretendard_light, FontWeight.Light),
        Font(R.font.pretendard_regular),
        Font(R.font.pretendard_medium, FontWeight.Medium),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold),
        Font(R.font.pretendard_bold, FontWeight.Bold),
        Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),
        Font(R.font.pretendard_black, FontWeight.Black),
    )

    MaterialTheme(
        typography = Typography(
            bodyMedium = androidx.compose.ui.text.TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
    ) {
        AppNavigation(navController)
    }
}