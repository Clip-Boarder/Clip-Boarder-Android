package com.clipboarder.clipboarder.ui.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clipboarder.clipboarder.R
import com.clipboarder.clipboarder.ui.styles.TextStyles

/**
 * Onboarding page.
 *
 * A page of the onboarding screen.
 *
 * @param title The title of the page.
 * @param description The description of the page.
 * @param imageResourceId The resource ID of the image to be displayed on the page.
 * @param modifier The modifier to be applied to the page.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun OnboardingPage(title: String, description: String, imageResourceId: Int, modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(160.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = title,
                style = TextStyles.onboardingTitle
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                style = TextStyles.onboardingDescription,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun OnboardingPagePreview() {
    OnboardingPage(
        title = "Title",
        description = "Description",
        imageResourceId = R.drawable.onboarding_image_1,
        modifier = Modifier
    )
}