package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.clipboarder.clipboarder.BuildConfig
import com.clipboarder.clipboarder.data.remote.dto.ContentDto
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository

/**
 * Clipboarder keyboard.
 *
 * Clipboarder keyboard for clipboarder.
 *
 * @param inputMethodService input method service
 * @param userRepository user repository
 * @param contentRepository content repository
 */
@Composable
fun ClipboarderKeyboard(
    inputMethodService: InputMethodService,
    userRepository: UserRepository,
    contentRepository: ContentRepository,
    viewModel: ClipboarderKeyboardViewModel = hiltViewModel()
) {
    viewModel.setRepositories(userRepository, contentRepository)
    viewModel.loadContentList()

    val contentList by viewModel.contentList.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(contentList.size) { index ->
            ClipboarderContentItem(inputMethodService = inputMethodService, contentItem = contentList[index])
        }
    }
}

/**
 * Clipboarder content item.
 *
 * Clipboarder content item for clipboarder keyboard.
 *
 * @param inputMethodService input method service
 * @param contentItem content item
 */
@Composable
fun ClipboarderContentItem(inputMethodService: InputMethodService, contentItem: ContentDto.ContentObjectDto) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.LightGray)
            .clickable {
                when (contentItem.contentType) {
                    "text" -> {
                        inputMethodService.currentInputConnection?.commitText(
                            contentItem.content,
                            1
                        )
                    }

                    "image" -> {
                        inputMethodService.currentInputConnection?.commitText(
                            BuildConfig.clipboarderBaseUrl + contentItem.content,
                            1
                        )
                    }

                    else -> return@clickable
                }

            }
    ) {
        when (contentItem.contentType) {
            "text" -> Text(
                text = contentItem.content ?: "",
                modifier = Modifier.padding(8.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            "image" -> Image(
                painter = rememberAsyncImagePainter(BuildConfig.clipboarderBaseUrl + contentItem.content),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            else -> Unit
        }
    }
}