package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clipboarder.clipboarder.data.remote.dto.ContentDto
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Clipboarder keyboard view model.
 *
 * View model for clipboarder keyboard.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@HiltViewModel
class ClipboarderKeyboardViewModel @Inject constructor(
) : ViewModel() {
    private lateinit var userRepository: UserRepository
    private lateinit var contentRepository: ContentRepository

    private val _contentList = MutableStateFlow<List<ContentDto.ContentObjectDto>>(emptyList())
    val contentList: StateFlow<List<ContentDto.ContentObjectDto>> = _contentList

    /**
     * Set repositories.
     *
     * Set user and content repositories.
     *
     * @param userRepository user repository
     * @param contentRepository content repository
     */
    fun setRepositories(userRepository: UserRepository, contentRepository: ContentRepository) {
        this.userRepository = userRepository
        this.contentRepository = contentRepository
    }

    /**
     * Load content list.
     *
     * Load content list from clipboarder.
     */
    fun loadContentList() {
        viewModelScope.launch {
            try {
                contentRepository.getContentListFromClipboarder().collect { responseDto ->
                    if (responseDto.result!!) {
                        _contentList.value = responseDto.data?.contentList!!
                    } else {
                        throw Exception("Error: error while getting content list")
                    }
                }
            } catch (e: Exception) {
                _contentList.value = emptyList()
                Log.e("ClipboarderKeyboardViewModel", "Error: error while getting content list", e)
            }
        }
    }
}

