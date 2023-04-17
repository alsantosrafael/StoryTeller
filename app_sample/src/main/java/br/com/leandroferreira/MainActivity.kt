package br.com.leandroferreira

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Today
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.leandroferreira.theme.ApplicationComposeTheme
import br.com.leandroferreira.theme.BACKGROUND_VARIATION
import br.com.storyteller.StoryTellerTimeline
import br.com.storyteller.VideoFrameConfig
import br.com.storyteller.drawer.DefaultDrawers
import br.com.storyteller.model.StoryUnit
import br.com.storyteller.normalization.StepsNormalizationBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        VideoFrameConfig.configCoilForVideoFrame(this)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    ApplicationComposeTheme {
        Scaffold(
            topBar = { TopBar() }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                Body()
            }
        }
    }
}

@Composable
private fun TopBar() {
    TopAppBar(
        title = { Text(text = "Edit") },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actions = {
            TextButton(onClick = { }) {
                Text(
                    text = "Publish",
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 19.sp,
                        color = MaterialTheme.colors.onPrimary
                    )
                )
            }
        }
    )
}

@Composable
private fun Body() {
    val context = LocalContext.current

    val viewModel = HistoriesViewModel(
        StepsNormalizationBuilder.reduceNormalizations {
            defaultNormalizers()
        }
    )

    var history by remember { mutableStateOf(viewModel.normalizedHistories(context)) }

    Column {
        InfoHeader()

        StoryTellerTimeline(
            modifier = Modifier.fillMaxSize(),
            story = history.values.sorted(),
            drawers = DefaultDrawers.create(onCommand = { command ->
                when (command.type) {
                    "move_up" -> {
                        history = moveUp(command.step.localPosition, history, viewModel)
                    }

                    "move_down" -> {
                        history = moveDown(command.step.localPosition, history, viewModel)
                    }

                    "delete" -> {
                        history = history - command.step.localPosition
                    }
                }
            })
        )
    }
}

@Composable
private fun InfoHeader() {
    Column(
        modifier = Modifier
            .padding(bottom = 6.dp)
            .background(BACKGROUND_VARIATION)
    ) {
        var storyName by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }

        OutlinedTextField(
            value = storyName,
            onValueChange = { text -> storyName = text },
            label = {
                Text(
                    "Story Name",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Label, contentDescription = "")
            },
        )

        OutlinedTextField(
            value = date,
            onValueChange = { text -> date = text },
            label = {
                Text(
                    "When did it start?",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Today, contentDescription = "")
            }
        )
    }
}

private fun moveUp(
    position: Int,
    history: Map<Int, StoryUnit>,
    viewModel: HistoriesViewModel
): Map<Int, StoryUnit> {
    val thisStep = history[position]
    val upStep = history[position - 1]

    val mutableHistory = history.toMutableMap()
    upStep?.let { step ->
        mutableHistory[position] = step.copyWithNewPosition(position)
    }

    thisStep?.let { step ->
        mutableHistory[position - 1] =
            step.copyWithNewPosition(position - 1)
    }

    return mutableHistory.values
        .toList()
        .let(viewModel::normalizeHistories)
        .associateBy { it.localPosition }
}

private fun moveDown(
    position: Int,
    history: Map<Int, StoryUnit>,
    viewModel: HistoriesViewModel
): Map<Int, StoryUnit> {
    return moveUp(position + 1, history, viewModel)
}

