package com.clipboarder.clipboarder.service

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import android.view.View
import com.clipboarder.clipboarder.R

class ClipboarderKeyboardService : InputMethodService() {
    @SuppressLint("InflateParams")
    override fun onCreateInputView(): View {
        return layoutInflater.inflate(R.layout.keyboard_view, null)
    }
}