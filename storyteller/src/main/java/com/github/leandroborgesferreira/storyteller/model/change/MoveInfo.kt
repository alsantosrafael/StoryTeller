package com.github.leandroborgesferreira.storyteller.model.change

import com.github.leandroborgesferreira.storyteller.model.story.StoryStep

data class MoveInfo(val storyUnit: StoryStep, val positionFrom: Int, val positionTo: Int)
