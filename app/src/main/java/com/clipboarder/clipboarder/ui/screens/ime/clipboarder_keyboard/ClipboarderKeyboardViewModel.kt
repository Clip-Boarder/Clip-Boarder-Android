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

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _isLoadingNextPage = MutableStateFlow(false)
    val isLoadingNextPage: StateFlow<Boolean> = _isLoadingNextPage

    private val _isLastPage = MutableStateFlow(false)
    val isLastPage: StateFlow<Boolean> = _isLastPage

    private val _contentList = MutableStateFlow<List<ContentDto.ContentObjectDto>>(emptyList())
    val contentList: StateFlow<List<ContentDto.ContentObjectDto>> = _contentList

    private var currentPage = 0
    private var loadingPage = 0

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
    suspend fun loadContentList() {
        Log.d("ClipboarderKeyboardViewModel", "Load content list by page: $loadingPage")

        try {
            contentRepository.getContentListFromClipboarder(loadingPage).collect { responseDto ->
                if (!responseDto.result!!) {
                    throw Exception("Error: error while getting content list")
                } else {
                    if (responseDto.data?.contentList!!.isNotEmpty()) {
                        _contentList.value += responseDto.data.contentList
                        currentPage = loadingPage
                    } else {
                        loadingPage = currentPage
                        _isLastPage.value = true
                    }
                }
            }
        } catch (e: Exception) {
            loadingPage = currentPage
            Log.e("ClipboarderKeyboardViewModel", "Error: error while getting content list", e)
        }
    }

    /**
     * Load first page.
     *
     * Load first page of the content list.
     */
    fun loadFirstPage() {
        if (isRefreshing.value || isLoadingNextPage.value) {
            Log.d("ClipboarderKeyboardViewModel", "Load first page canceled")
            return
        }

        Log.d("ClipboarderKeyboardViewModel", "Load first page")

        _contentList.value = emptyList()
        _isRefreshing.value = true
        _isLastPage.value = false
        loadingPage = 1

        viewModelScope.launch {
            loadContentList()
            _isRefreshing.value = false
        }
    }

    /**
     * Load next page.
     *
     * Load next page of the content list.
     */
    fun loadNextPage() {
        if (isRefreshing.value || isLoadingNextPage.value) {
            Log.d("ClipboarderKeyboardViewModel", "Load next page canceled")
            return
        }

        _isLoadingNextPage.value = true
        Log.d("ClipboarderKeyboardViewModel", "Load next page")

        viewModelScope.launch {
            loadingPage++
            loadContentList()
            _isLoadingNextPage.value = false
        }
    }

    /**
     * Clear content list.
     *
     * Clear content list and reset page index.
     */
    fun clearContentList() {
        _isLastPage.value = false
        _contentList.value = emptyList()
        currentPage = 0
        loadingPage = 0
    }
}

