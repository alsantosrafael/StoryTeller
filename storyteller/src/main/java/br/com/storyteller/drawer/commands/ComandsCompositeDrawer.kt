package br.com.storyteller.drawer.commands

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.storyteller.drawer.StoryUnitDrawer
import br.com.storyteller.model.Command
import br.com.storyteller.model.StoryUnit

class CommandsCompositeDrawer(
    private val innerStep: StoryUnitDrawer,
    private val onMoveUp: (Command) -> Unit = {},
    private val onMoveDown: (Command) -> Unit = {},
    private val onDelete: (Command) -> Unit = {}
) : StoryUnitDrawer {

    @Composable
    override fun Step(step: StoryUnit) {
        Box(modifier = Modifier.padding(horizontal = 3.dp)) {
            Box(modifier = Modifier.padding(top = 3.dp)) {
                innerStep.Step(step = step)
            }

            DeleteButton(step)
            MoveUpButton(step)
            MoveDownButton(step)
        }
    }

    @Composable
    private fun BoxScope.DeleteButton(step: StoryUnit) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = CircleShape
                )
                .background(Color.LightGray)
        ) {
            IconButton(onClick = { onDelete(Command(type = "delete", step)) }) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

    @Composable
    private fun BoxScope.MoveUpButton(step: StoryUnit) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = CircleShape
                )
                .background(Color.LightGray)
                .align(Alignment.TopCenter)
        ) {
            IconButton(onClick = { onDelete(Command(type = "move_up", step)) }) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowUp,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
    @Composable
    private fun BoxScope.MoveDownButton(step: StoryUnit) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = CircleShape
                )
                .background(Color.LightGray)
                .align(Alignment.BottomCenter)
        ) {
            IconButton(onClick = { onMoveDown(Command(type = "move_down", step)) }) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

}
