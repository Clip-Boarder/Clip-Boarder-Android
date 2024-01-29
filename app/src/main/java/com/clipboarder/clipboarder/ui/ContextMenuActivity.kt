package com.clipboarder.clipboarder.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity


class ContextMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        // 모든 이미지의 경로를 하나의 텍스트로 합쳐서 Toast로 보내기
        val imageUriList = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)
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
            Toast.makeText(this, "선택한 텍스트: $selectedText", Toast.LENGTH_SHORT).show()
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