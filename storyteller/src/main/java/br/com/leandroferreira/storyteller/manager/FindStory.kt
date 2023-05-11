package br.com.leandroferreira.storyteller.manager

import br.com.leandroferreira.storyteller.model.story.StoryUnit

/**
 * Helper object to find StoryUnits inside a the List<Story>. This object search in the list and
 * also in GroupSteps.
 */
object FindStory {

    /**
     * Todo: Add unit test
     */
    fun previousFocus(
        storyList: List<StoryUnit>,
        localPosition: Int,
        focusableTypes: Set<String>
    ): StoryUnit? {
        for (i in (localPosition - 1) downTo 0) {
            if (focusableTypes.contains(storyList[i].type)) {
                return storyList[i]
            }
        }

        return null
    }

}
