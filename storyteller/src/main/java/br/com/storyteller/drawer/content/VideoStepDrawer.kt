package br.com.storyteller.drawer.content

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import br.com.storyteller.drawer.StoryUnitDrawer
import br.com.storyteller.model.StoryStep
import br.com.storyteller.model.StoryUnit
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.videoFrameMillis

class VideoStepDrawer : StoryUnitDrawer {

    @Composable
    override fun Step(step: StoryUnit) {
        val videoStep = step as StoryStep

        Box(modifier = Modifier.padding(vertical = 3.dp)) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(size = 12.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .align(Alignment.Center)
            ) {
                val request = ImageRequest.Builder(LocalContext.current)
                    .data(videoStep.url?.toUri())
                    .videoFrameMillis(1000)
                    .build()

                AsyncImage(
                    model = request,
                    contentDescription = "",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
            }

            Icon(
                imageVector = Icons.Outlined.VideoLibrary,
                contentDescription = "",
                modifier = Modifier.align(alignment = Alignment.TopEnd)
                    .padding(5.dp)
            )
        }
    }
}
