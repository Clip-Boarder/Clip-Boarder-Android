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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipboarder.clipboarder.R

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
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                softWrap = true,
                modifier = Modifier.padding(horizontal = 48.dp)
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