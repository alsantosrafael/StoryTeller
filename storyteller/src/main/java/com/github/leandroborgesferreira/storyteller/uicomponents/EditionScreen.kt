package com.github.leandroborgesferreira.storyteller.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.leandroborgesferreira.storyteller.R

@Composable
@Preview
// This screen could live in a module for extra Composables
fun EditionScreen(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {}
) {
    Box(modifier = modifier) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(50.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onDelete),
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = stringResource(R.string.delete),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}