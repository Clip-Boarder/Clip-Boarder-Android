package com.clipboarder.clipboarder.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ContextMenuActivity
 *
 * This activity is used to handle the context menu actions.
 *
 * @author YoungJin Sohn
 * @since v1.0.0
 */
@AndroidEntryPoint
class ContextMenuActivity : ComponentActivity() {
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var contentRepository: ContentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (userRepository.getAccessToken() == null) {
            Toast.makeText(this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show()
            return
        }

        val action = intent.action
        intent.type?.let { type ->
            when (action) {
                Intent.ACTION_PROCESS_TEXT -> {
                    if ("text/plain" == type) {
                        uploadText(intent)
                    }
                }
                Intent.ACTION_SEND -> {
                    when {
                        type.startsWith("image/") -> uploadImage(intent)
                        type.startsWith("text/") -> {
                            uploadTexts(intent)
                        }
                    }
                }
                Intent.ACTION_SEND_MULTIPLE -> {
                    when {
                        type.startsWith("image/") -> uploadImages(intent)
                        type.startsWith("text/") -> uploadTexts(intent)
                    }
                }
            }
        }

        finish()
    }

    private fun uploadImage(intent: Intent) {
        val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        imageUri?.let {
            Toast.makeText(this, "이미지 경로: $imageUri", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "이미지가 없습니다", Toast.LENGTH_SHORT).show()
    }

    private fun uploadImages(intent: Intent) {
        val imageUriList = if (VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM)
        }
        imageUriList?.let {
            val combinedText = imageUriList.joinToString(separator = "\n") { uri ->
                uri.path.toString()
            }
            Toast.makeText(this, combinedText, Toast.LENGTH_LONG).show()
        } ?: Toast.makeText(this, "이미지가 없습니다", Toast.LENGTH_SHORT).show()
    }

    private fun uploadText(intent: Intent) {
        val selectedText = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        if (selectedText != null) {
            lifecycleScope.launch {
                contentRepository.copyTextToClipboarder(selectedText.toString())
                    .catch { e ->
                        // Handle any errors, e.g., show a toast message
                        Toast.makeText(
                            this@ContextMenuActivity,
                            "Error: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .collect { response ->
                        Toast.makeText(
                            this@ContextMenuActivity,
                            "Text uploaded successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        } else {
            Toast.makeText(this, "선택한 텍스트가 없습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadTexts(intent: Intent) {
        Toast.makeText(this, "여러 텍스트를 선택했습니다", Toast.LENGTH_SHORT).show()
        val selectedTexts = intent.getCharSequenceArrayListExtra(Intent.EXTRA_PROCESS_TEXT)
        selectedTexts?.let {
            val combinedText = selectedTexts.joinToString(separator = "\n") { text ->
                text.toString()
            }
            Toast.makeText(this, combinedText, Toast.LENGTH_LONG).show()
        } ?: Toast.makeText(this, "텍스트가 없습니다", Toast.LENGTH_SHORT).show()
    }
}