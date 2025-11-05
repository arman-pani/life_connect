package com.example.lifeconnect.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun CustomClickableText(
    startingText: String,
    clickableText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val annotatedText = buildAnnotatedString {
        append(startingText)
        pushStringAnnotation(
            tag = "clickable",
            annotation = clickableText
        )

        withStyle(
            style = SpanStyle(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
            )
        ) { append(clickableText) }

        pop()
    }

    BasicText(
        text = annotatedText,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                // Detect click within the annotated text range
                annotatedText.getStringAnnotations(
                    tag = "clickable",
                    start = offsetToIndex(offset, annotatedText),
                    end = offsetToIndex(offset, annotatedText)
                ).firstOrNull()?.let {
                    onClick()
                }
            }
        }
    )
}

fun offsetToIndex(offset: Offset, annotatedString: AnnotatedString): Int {
    // This is a simplified placeholder — Compose doesn’t expose offset-to-index directly for BasicText
    // You should use a TextLayoutResult from onTextLayout in real apps for accurate mapping.
    return offset.x.toInt().coerceIn(0, annotatedString.length - 1)
}