package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import androidx.lifecycle.ViewModel
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClipboarderKeyboardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val contentRepository: ContentRepository
) : ViewModel() {

}