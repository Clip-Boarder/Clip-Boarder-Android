package com.clipboarder.clipboarder.ui.screens.ime.language_keyboard

import android.inputmethodservice.InputMethodService
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipboarder.clipboarder.ui.screens.ime.language_keyboard.english_keyboard.EnglishKeyboard
import com.clipboarder.clipboarder.ui.screens.ime.language_keyboard.korean_keyboard.KoreanKeyboard

@Composable
fun LanguageKeyboard(
    inputMethodService: InputMethodService,
    viewModel: LanguageKeyboardViewModel = hiltViewModel()
) {
    when (viewModel.language) {
        "korean" -> KoreanKeyboard(inputMethodService, viewModel::toggleLanguage)
        "english" -> EnglishKeyboard(inputMethodService, viewModel::toggleLanguage)
        else -> KoreanKeyboard(inputMethodService, viewModel::toggleLanguage)
    }
}