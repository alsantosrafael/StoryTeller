package com.github.leandroborgesferreira.storyteller.parse

import com.github.leandroborgesferreira.storyteller.model.story.StoryStep
import com.github.leandroborgesferreira.storyteller.model.story.StoryType

class PreviewParser(
    private val acceptedTypes: Set<String> = defaultTypes()
) {

    fun preview(stories: Iterable<StoryStep>, maxSize: Int = 4): List<StoryStep> {
        var acc = 0

        return stories.asSequence()
            .filter { storyStep -> acceptedTypes.contains(storyStep.type)  }
            .takeWhile { acc++ < maxSize }
            .toList()
    }
}

private fun defaultTypes() = setOf(
    StoryType.TITLE.type,
    StoryType.MESSAGE.type,
    StoryType.CHECK_ITEM.type,
)
