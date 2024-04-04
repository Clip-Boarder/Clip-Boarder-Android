package com.clipboarder.clipboarder.ui.screens.ime.language_keyboard.korean_keyboard

import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun KoreanKeyboard(
    inputMethodService: InputMethodService,
    toggleLanguage: () -> Unit,
    viewModel: KoreanKeyboardViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = toggleLanguage) {
            Text("영어로 변경")
        }
    }
}