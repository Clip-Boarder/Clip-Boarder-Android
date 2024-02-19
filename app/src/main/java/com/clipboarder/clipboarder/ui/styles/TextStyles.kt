package com.clipboarder.clipboarder.ui.styles

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.clipboarder.clipboarder.R

object TextStyles {
    private val fontFamily = FontFamily(
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

    private const val fontRescale = 1.0

    val onboardingTitle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 32.sp,
        letterSpacing = (-0.03).em,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )

    val onboardingDescription = TextStyle(
        fontFamily = fontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.03).em,
        textAlign = TextAlign.Center,
    )

    val onboardingActionButton = TextStyle(
        fontFamily = fontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.03).em,
        textAlign = TextAlign.Center
    )

    val loginTitleMain = TextStyle(
        fontFamily = fontFamily,
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.03).em,
        textAlign = TextAlign.Center
    )

    val loginActionTitle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.03).em,
        textAlign = TextAlign.Center
    )

    val loginButton = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.em,
        textAlign = TextAlign.Center,
        color = Color(0xff767676)
    )
}