package com.clipboarder.clipboarder.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clipboarder.clipboarder.data.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Login screen view model.
 *
 * View model for login screen.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _isLoginProcessing = MutableStateFlow(false)
    val isLoginProcessing = _isLoginProcessing.asStateFlow()

    private val _isLoginSuccess = MutableStateFlow<Boolean?>(null)
    val isLoginSuccess = _isLoginSuccess.asStateFlow()

    fun startLoginProcess() {
        _isLoginProcessing.value = true
    }

    fun endLoginProcess() {
        _isLoginProcessing.value = false
    }

    fun signInWithGoogleAccount(account: GoogleSignInAccount) {
        _isLoginSuccess.value = null
        viewModelScope.launch {
            try {
                userRepository.signIn(
                    googleIdToken = account.idToken!!
                ).collect { responseDto ->
                    if (responseDto.result!!) {
                        userRepository.saveLoginInfo(
                            accessToken = responseDto.data?.accessToken!!,
                            refreshToken = responseDto.data.refreshToken!!,
                            email = account.email!!)
                        Log.d("LoginScreenViewModel", "Token: ${responseDto.data.accessToken}")
                        _isLoginSuccess.value = true
                    } else {
                        Log.d("LoginScreenViewModel", "Error: error while signing up")
                        _isLoginSuccess.value = false
                    }
                }
            } catch (e: Exception) {
                _isLoginSuccess.value = false
                Log.d("LoginScreenViewModel", "Error in googleSignIn: $e")
                e.printStackTrace()
            } finally {
                endLoginProcess()
            }
        }
    }
}