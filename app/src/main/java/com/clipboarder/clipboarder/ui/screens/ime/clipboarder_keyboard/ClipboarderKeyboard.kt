package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipboarder.clipboarder.ui.screens.login.LoginScreenViewModel

@Composable
fun ClipboarderKeyboard(
    inputMethodService: InputMethodService,
    viewModel: ClipboarderKeyboardViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "클립보더 키보드 영역")
    }
}