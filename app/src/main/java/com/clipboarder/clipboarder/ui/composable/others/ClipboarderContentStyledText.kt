package com.clipboarder.clipboarder.ui.composable.others

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Clipboarder content styled text.
 *
 * Clipboarder content styled text for clipboarder. It shows the first 4 characters in bold and the rest in normal.
 *
 * @param content text content
 */
@Composable
fun ClipboarderContentStyledText(content: String) {
    val annotatedString = with(AnnotatedString.Builder()) {
        if (content.length >= 4) {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
            append(content.substring(0, 4))
            pop()
        } else {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
            append(content)
            pop()
        }

        if (content.length > 4) {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp))
            append(content.substring(4))
            pop()
        }
        toAnnotatedString()
    }

    Text(
        text = annotatedString,
        modifier = Modifier.padding(8.dp),
        maxLines = 5,
        overflow = TextOverflow.Ellipsis
    )
}
