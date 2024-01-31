package com.clipboarder.clipboarder.ui.screens.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Onboarding screen view model.
 *
 * View model for onboarding screen.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
class OnboardingScreenViewModel : ViewModel() {
    /**
     * Current page of the onboarding screen.
     */
    private val _currentPage = MutableLiveData<Int>(0)
    val currentPage: LiveData<Int> = _currentPage


    /**
     * Navigates to the previous page.
     */
    fun goToPreviousPage() {
        _currentPage.value = (_currentPage.value ?: 0) - 1
    }

    /**
     * Navigates to the next page.
     */
    fun goToNextPage() {
        _currentPage.value = (_currentPage.value ?: 0) + 1
    }
}