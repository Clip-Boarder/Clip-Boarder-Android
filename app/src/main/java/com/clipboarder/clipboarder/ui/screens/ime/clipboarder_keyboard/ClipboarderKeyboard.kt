package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import android.inputmethodservice.InputMethodService
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository

@Composable
fun ClipboarderKeyboard(
    inputMethodService: InputMethodService,
    userRepository: UserRepository,
    contentRepository: ContentRepository,
    viewModel: ClipboarderKeyboardViewModel = hiltViewModel()
) {
    viewModel.setRepositories(userRepository, contentRepository)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "클립보더 키보드 영역")
        Button(onClick = {

        }) {
            Text(text = "토큰 가져오기")
        }
    }
}