package com.clipboarder.clipboarder.service

import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.view.View
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ServiceLifecycleDispatcher
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import com.clipboarder.clipboarder.ui.screens.ime.ClipboarderIME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * ClipboarderKeyboardService
 *
 * This service is used to handle the keyboard actions.
 *
 * @since 1.0.0
 * @author YoungJin
 */
@AndroidEntryPoint
class ClipboarderKeyboardService : InputMethodService(), LifecycleOwner,
    ViewModelStoreOwner, SavedStateRegistryOwner {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var contentRepository: ContentRepository

    private fun onCreateInputComposeView(): AbstractComposeView {
        return object : AbstractComposeView(this) {
            @Composable
            override fun Content() {
                ClipboarderIME(this@ClipboarderKeyboardService, userRepository, contentRepository)
            }
        }
    }

    private val lifecycleDispatcher = ServiceLifecycleDispatcher(this)
    override val lifecycle: Lifecycle
        get() = lifecycleDispatcher.lifecycle

    override val viewModelStore = ViewModelStore()

    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

    @CallSuper
    override fun onCreate() {
        lifecycleDispatcher.onServicePreSuperOnCreate()
        super.onCreate()
        savedStateRegistryController.performRestore(null)
    }

    @CallSuper
    override fun onBindInput() {
        lifecycleDispatcher.onServicePreSuperOnBind()
        super.onBindInput()
    }

    @CallSuper
    override fun onCreateInputView(): View {
        val view = onCreateInputComposeView().apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle))
        }

        window?.window?.decorView?.let { decorView ->
            decorView.setViewTreeLifecycleOwner(this)
            decorView.setViewTreeViewModelStoreOwner(this)
            decorView.setViewTreeSavedStateRegistryOwner(this)
        }

        return view
    }

    @CallSuper
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    @CallSuper
    override fun onDestroy() {
        lifecycleDispatcher.onServicePreSuperOnDestroy()
        super.onDestroy()
    }
}