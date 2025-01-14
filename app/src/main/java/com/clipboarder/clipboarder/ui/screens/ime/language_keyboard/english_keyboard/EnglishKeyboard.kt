package com.clipboarder.clipboarder.ui.screens.ime.language_keyboard.english_keyboard

import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EnglishKeyboard(
    inputMethodService: InputMethodService,
    toggleLanguage: () -> Unit,
    viewModel: EnglishKeyboardViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            toggleLanguage()
        }) {
            Text(text = "한글로 변경")
        }
    }
}