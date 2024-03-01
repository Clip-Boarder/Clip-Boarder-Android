package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import androidx.lifecycle.ViewModel
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClipboarderKeyboardViewModel @Inject constructor(
) : ViewModel() {
    private lateinit var userRepository: UserRepository
    private lateinit var contentRepository: ContentRepository

    fun setRepositories(userRepository: UserRepository, contentRepository: ContentRepository) {
        this.userRepository = userRepository
        this.contentRepository = contentRepository
    }
}

