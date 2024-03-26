package com.clipboarder.clipboarder.ui.screens.ime.clipboarder_keyboard

import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClipboarderKeyboard(
    inputMethodService: InputMethodService,
    userRepository: UserRepository,
    contentRepository: ContentRepository,
    viewModel: ClipboarderKeyboardViewModel = hiltViewModel()
) {
    viewModel.setRepositories(userRepository, contentRepository)

    val contentList by viewModel.contentList.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isLoadingNextPage by viewModel.isLoadingNextPage.collectAsState()

    val pullToRefreshState = rememberPullToRefreshState()
    val lazyGridState = rememberLazyGridState()
    var lastLoadedItemIndex = 0

    // Load first page
    LaunchedEffect(Unit) {
        viewModel.loadFirstPage()
    }

    // Load content list when refreshing
    LaunchedEffect(pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) {
            pullToRefreshState.endRefresh()
            viewModel.loadFirstPage()
        }
    }

    // Load next page when the last item is visible
    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: 0
                if (lastLoadedItemIndex != lastVisibleItemIndex && lastVisibleItemIndex >= contentList.size - 1) {
                    viewModel.loadNextPage()
                    lastLoadedItemIndex = lastVisibleItemIndex
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        if (isRefreshing) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        } else {
            Column {
                LazyVerticalGrid(
                    state = lazyGridState,
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(contentList.size) { index ->
                        ClipboarderContentItem(
                            inputMethodService = inputMethodService,
                            contentItem = contentList[index]
                        )
                    }
                }
                if (isLoadingNextPage) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter), state = pullToRefreshState
            )
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
fun ClipboarderContentItem(
    inputMethodService: InputMethodService,
    contentItem: ContentDto.ContentObjectDto
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
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