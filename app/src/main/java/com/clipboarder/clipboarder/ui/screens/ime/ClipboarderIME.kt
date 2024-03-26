package com.clipboarder.clipboarder.ui.screens.ime

import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard.ClipboarderKeyboard
import com.clipboarder.clipboarder.ui.screens.ime.language_keyboard.LanguageKeyboard


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClipboarderIME(
    inputMethodService: InputMethodService,
    userRepository: UserRepository,
    contentRepository: ContentRepository
) {
    val pagerState = rememberPagerState(
        pageCount = { 1000 },
        initialPage = 1000 / 2
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(288.dp)
            .background(Color.DarkGray)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page % 2) {
                0 -> LanguageKeyboard(inputMethodService)
                1 -> ClipboarderKeyboard(inputMethodService, userRepository, contentRepository)
            }
        }
    }
}