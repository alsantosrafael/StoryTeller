package com.github.leandroborgesferreira.storyteller.drawer.content

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.github.leandroborgesferreira.storyteller.drawer.DrawInfo
import com.github.leandroborgesferreira.storyteller.drawer.StoryUnitDrawer
import com.github.leandroborgesferreira.storyteller.drawer.modifier.callOnEmptyErase
import com.github.leandroborgesferreira.storyteller.model.change.DeleteInfo
import com.github.leandroborgesferreira.storyteller.model.story.StoryStep
import com.github.leandroborgesferreira.storyteller.text.edition.TextCommandHandler
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Draw a text that can be edited. The edition of the text is both reflect in this Composable and
 * also notified by onTextEdit. It is necessary to reflect here to avoid losing the focus on the
 * TextField.
 */
class MessageDrawer(
    private val containerModifier: Modifier = Modifier,
    private val innerContainerModifier: Modifier = Modifier,
    private val onTextEdit: (String, Int) -> Unit,
    private val onDeleteRequest: (DeleteInfo) -> Unit,
    private val commandHandler: TextCommandHandler,
    private val onSelected: (Int) -> Unit
) : StoryUnitDrawer {

    @Composable
    override fun LazyItemScope.Step(step: StoryStep, drawInfo: DrawInfo) {
        val backgroundColor = MaterialTheme.colorScheme.background
        val backgroundEditColor = MaterialTheme.colorScheme.primary

        val focusRequester = remember { FocusRequester() }
        var isOnEditMode by remember { mutableStateOf(false) }
        val haptic = LocalHapticFeedback.current
        var swipeOffset by remember { mutableStateOf(0F) }

        val transition = updateTransition(
            targetState = isOnEditMode,
            label = "editionMultipleAnimation"
        )

        val colorAnimated by transition.animateColor(label = "colorAnimation") { isEdit ->
            if (isEdit) backgroundEditColor else backgroundColor
        }

        val animatedOffset by transition.animateIntOffset(
            transitionSpec = {
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    visibilityThreshold = IntOffset(1, 1)
                )
            },
            label = "offsetAnimation",
        ) { isEdit ->
            if (isEdit) IntOffset(0, 0) else IntOffset(swipeOffset.roundToInt(), 0)
        }

        Box(modifier = containerModifier
            .offset {
                if (isOnEditMode) {
                    animatedOffset
                } else {
                    IntOffset(swipeOffset.roundToInt(), 0)
                }
            }
            .background(colorAnimated)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { _ -> },
                    onHorizontalDrag = { _, dragAmount ->
                        val maxDistance = 80
                        val correction = (maxDistance - swipeOffset.absoluteValue) / maxDistance

                        if (!isOnEditMode) {
                            swipeOffset += dragAmount * correction.pow(3)
                        }
                    },
                    onDragCancel = {
                        swipeOffset = 0F
                    },
                    onDragEnd = {
                        if (swipeOffset.absoluteValue > 40) {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            isOnEditMode = true
                        }

                        swipeOffset = 0F
                    })
            }
            .clickable {
                focusRequester.requestFocus()
            }) {
            if (drawInfo.editable) {
                var inputText by remember {
                    val text = step.text ?: ""
                    mutableStateOf(TextFieldValue(text, TextRange(text.length)))
                }

                LaunchedEffect(drawInfo.focusId) {
                    if (drawInfo.focusId == step.id) {
                        focusRequester.requestFocus()
                    }
                }

                BasicTextField(
                    modifier = innerContainerModifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .callOnEmptyErase(inputText.selection) {
                            onDeleteRequest(DeleteInfo(step, drawInfo.position))
                        },
                    value = inputText,
                    onValueChange = { value ->
                        if (!commandHandler.handleCommand(
                                value.text,
                                step,
                                drawInfo.position
                            )
                        ) {
                            inputText = value
                            onTextEdit(value.text, drawInfo.position)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
                )
            } else {
                Text(
                    text = step.text ?: "",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                )
            }
        }
    }
}
