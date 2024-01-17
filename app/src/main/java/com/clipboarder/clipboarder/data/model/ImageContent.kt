package com.clipboarder.clipboarder.data.model

data class ImageContent(
    override val contentId: String,
    override val copiedTimestamp: Long,
    val imageUrl: String
) : ClipboardContent