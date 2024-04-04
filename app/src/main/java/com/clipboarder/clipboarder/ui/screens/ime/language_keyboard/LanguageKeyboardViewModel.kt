package com.clipboarder.clipboarder.ui.screens.ime.language_keyboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageKeyboardViewModel @Inject constructor(
) : ViewModel() {
    var language by mutableStateOf("korean")
        private set

    fun toggleLanguage() {
        language = if (language == "korean") "english" else "korean"
    }
}