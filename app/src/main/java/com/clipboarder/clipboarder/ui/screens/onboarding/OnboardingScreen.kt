package com.clipboarder.clipboarder.ui.screens.onboarding

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.clipboarder.clipboarder.R
import com.clipboarder.clipboarder.ui.screens.login.LOGIN_SCREEN
import com.clipboarder.clipboarder.ui.screens.onboarding.components.OnboardingPage

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
fun OnboardingScreen(navController: NavController) {
    val context: Context = LocalContext.current
    val viewModel: OnboardingScreenViewModel = hiltViewModel()
    val currentPage = viewModel.currentPage.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        when (currentPage.value) {
            0 -> OnboardingPage(
                title = context.getString(R.string.onboarding_page1_title),
                description = context.getString(R.string.onboarding_page1_description),
                imageResourceId = R.drawable.onboarding_image_1,
                modifier = Modifier.weight(1f)
            )

            1 -> OnboardingPage(
                title = context.getString(R.string.onboarding_page2_title),
                description = context.getString(R.string.onboarding_page2_description),
                imageResourceId = R.drawable.onboarding_image_2,
                modifier = Modifier.weight(1f)
            )

            2 -> OnboardingPage(
                title = context.getString(R.string.onboarding_page3_title),
                description = context.getString(R.string.onboarding_page3_description),
                imageResourceId = R.drawable.onboarding_image_3,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (currentPage.value!! > 0) {
                // If current page is not first page, show previous button
                Button(
                    onClick = { viewModel.goToPreviousPage() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xff767676)
                    ),
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        context.getString(R.string.onboarding_previous_page),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (currentPage.value == 2) {
                // If current page is last page, change next button text
                Button(
                    onClick = {
                        navController.navigate(LOGIN_SCREEN) {
                            popUpTo(ONBOARDING_SCREEN) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xff767676)
                    ),
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        context.getString(R.string.onboarding_done),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                Button(
                    onClick = { viewModel.goToNextPage() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xff767676)
                    ),
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        context.getString(R.string.onboarding_next_page),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}
