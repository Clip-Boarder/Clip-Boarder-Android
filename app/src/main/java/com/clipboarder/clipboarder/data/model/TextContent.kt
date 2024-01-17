package com.clipboarder.clipboarder.data.model

data class TextContent(
    override val contentId: String,
    override val copiedTimestamp: Long,
    val text: String
) : ClipboardContent