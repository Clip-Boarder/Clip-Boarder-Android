package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipboarder.clipboarder.ui.screens.login.LoginScreenViewModel

@Composable
fun ClipboarderKeyboard(viewModel: LoginScreenViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}