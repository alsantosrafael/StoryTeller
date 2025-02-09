package com.github.leandroborgesferreira.storyteller.drawer.commands

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.leandroborgesferreira.storyteller.drawer.DrawInfo
import com.github.leandroborgesferreira.storyteller.drawer.StoryStepDrawer
import com.github.leandroborgesferreira.storyteller.model.action.Action
import com.github.leandroborgesferreira.storyteller.model.story.StoryStep

/**
 * Drawer for commands. This drawer adds commands for move up, move down and delete on top of any
 * Composable. If this [CommandsDecoratorDrawer] at the moment it is only possible to have all 3
 * buttons, later it will be added the possibility to chose which button appears to the user.
 */
class CommandsDecoratorDrawer(
    private val innerStepDrawer: StoryStepDrawer,
    private val onDelete: (Action.DeleteStory) -> Unit = {}
) : StoryStepDrawer {

    @Composable
    override fun Step(step: StoryStep, drawInfo: DrawInfo) {
        Box {
            Box(modifier = Modifier.padding(top = 3.dp)) {
                innerStepDrawer.Step(step = step, drawInfo = drawInfo)
            }

            DeleteButton(step, drawInfo.position)
        }
    }

    @Composable
    private fun BoxScope.DeleteButton(step: StoryStep, position: Int) {
        Box(
            modifier = Modifier
                .buttonModifier()
                .align(Alignment.TopStart)
        ) {
            IconButton(onClick = { onDelete(Action.DeleteStory(step, position)) }) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

    private fun Modifier.buttonModifier() = this
        .clip(shape = CircleShape)
        .border(
            width = 1.dp,
            color = Color.Gray,
            shape = CircleShape
        )
        .background(Color.LightGray)
        .size(30.dp)
}
