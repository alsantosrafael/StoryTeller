package com.github.leandroborgesferreira.storytellerapp.note_menu.data

import android.content.Context
import com.github.leandroborgesferreira.storyteller.model.story.StoryStep
import com.github.leandroborgesferreira.storyteller.model.story.StoryType
import java.util.UUID

fun messages(): List<StoryStep> = buildList {
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "message_box",
            text = "We arrived in Santiago!! \n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        )
    )
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "message_box",
            text = "And it was super awesome!! \n\nUt enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?",
        )
    )
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "message_box",
            text = "I couldn't believe how sunny it was. Santiago is a really beautiful city. There's a lot to " +
                "do and enjoyed the day. We went to many cozy Cafes and we enjoyed the city by foot. " +
                "We had to buy some Syn Cards to be able to communicate in the new country. ",
        )
    )
}

fun messagesMap(): Map<Int, StoryStep> =
    mapOf(
        0 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "message_box",
                text = "We arrived in Santiago!! \n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            ),
        1 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "message_box",
                text = "And it was super awesome!! \n\nUt enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?",
            ),
        2 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "message_box",
                text = "I couldn't believe how sunny it was. Santiago is a really beautiful city. There's a lot to " +
                    "do and enjoyed the day. We went to many cozy Cafes and we enjoyed the city by foot. " +
                    "We had to buy some Syn Cards to be able to communicate in the new country. ",
            )
    )

fun images(): List<StoryStep> = buildList {
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/15/400/400.jpg?hmac=xv-6mggpYPLIQ9eNAHrl1qKPHjyUCYlBoNBvdsqF4cY",
        )
    )
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/1018/400/400.jpg?hmac=MwHJoMaVXsBbqg-LFoDVL6P8TCDkSEikExptCkkHESQ",
            text = "1"
        )
    )
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/984/400/400.jpg?hmac=CaqZ-rcUAbmidwURZcBynO7aIAC-FaktVN7X8lIvlmE",
            text = "2"
        )
    )
    add(
        StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/949/400/400.jpg?hmac=lGa4vz7HqqRP0noWMCCKo6Klo_MaYg6WpUulORAoVQU",
            text = "3"
        )
    )
}

fun imagesMap(): Map<Int, StoryStep> =
    mapOf(
        0 to StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/15/400/400.jpg?hmac=xv-6mggpYPLIQ9eNAHrl1qKPHjyUCYlBoNBvdsqF4cY",
        ),
        1 to StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/1018/400/400.jpg?hmac=MwHJoMaVXsBbqg-LFoDVL6P8TCDkSEikExptCkkHESQ",
            text = "1"
        ),
        2 to StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/984/400/400.jpg?hmac=CaqZ-rcUAbmidwURZcBynO7aIAC-FaktVN7X8lIvlmE",
            text = "2"
        ),
        3 to StoryStep(
            localId = UUID.randomUUID().toString(),
            type = "image",
            url = "https://fastly.picsum.photos/id/949/400/400.jpg?hmac=lGa4vz7HqqRP0noWMCCKo6Klo_MaYg6WpUulORAoVQU",
            text = "3"
        )
    )

fun supermarketList(): Map<Int, StoryStep> = mapOf(
    0 to StoryStep(
        localId = UUID.randomUUID().toString(),
        type = StoryType.TITLE.type,
        text = "Supermarket List",
    ),
    1 to StoryStep(
        localId = UUID.randomUUID().toString(),
        type = "check_item",
        text = "Corn",
    ),
    2 to StoryStep(
        localId = UUID.randomUUID().toString(),
        type = "check_item",
        text = "Chicken",
    ),
    3 to StoryStep(
        localId = UUID.randomUUID().toString(),
        type = "check_item",
        text = "Olives",
    ),
    4 to StoryStep(
        localId = UUID.randomUUID().toString(),
        type = "check_item",
        text = "Soup",
    )
)

fun travelHistory(context: Context): Map<Int, StoryStep> =
    mapOf(
        0 to StoryStep(
            localId = UUID.randomUUID().toString(),
            type = StoryType.TITLE.type,
            text = "Travel notes",
        ),
        1 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = StoryType.GROUP_IMAGE.type,
                steps = listOf(
                    StoryStep(
                        localId = UUID.randomUUID().toString(),
                        type = StoryType.IMAGE.type,
                        url = "https://fastly.picsum.photos/id/15/400/400.jpg?hmac=xv-6mggpYPLIQ9eNAHrl1qKPHjyUCYlBoNBvdsqF4cY",
                    ),
                    StoryStep(
                        localId = UUID.randomUUID().toString(),
                        type = "image",
                        url = "https://fastly.picsum.photos/id/1018/400/400.jpg?hmac=MwHJoMaVXsBbqg-LFoDVL6P8TCDkSEikExptCkkHESQ",
                    ),
                    StoryStep(
                        localId = UUID.randomUUID().toString(),
                        type = "image",
                        url = "https://fastly.picsum.photos/id/984/400/400.jpg?hmac=CaqZ-rcUAbmidwURZcBynO7aIAC-FaktVN7X8lIvlmE",
                    )
                ),
            ),
        2 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "message",
                text = "We arrived in Santiago!!",
            ),
        3 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "check_item",
                text = "We need to go to the Cafe!",
            ),
        4 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "check_item",
                text = "We need to have lots of fun!",
            ),
        5 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "check_item",
                text = "We need to lear some Spanish!",
            ),
        6 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "image",
                url = "https://fastly.picsum.photos/id/514/1200/600.jpg?hmac=gh5_PZFkQI74GShPTCJ_XP_EgN-X1O0OUP8tDlT7WkY",
                title = "The hotel entrance"
            ),
        7 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "message_box",
                text = "And it was super awesome!! \n\nUt enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?",
            ),
        8 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "video",
//                path = "android.resource://${context.packageName}/${R.raw.video}",
                url = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
            ),
        9 to
            StoryStep(
                localId = UUID.randomUUID().toString(),
                type = "message_box",
                text = "I couldn't believe how sunny it was. Santiago is a really beautiful city. There's a lot to " +
                    "do and enjoyed the day. We went to many cozy Cafes and we enjoyed the city by foot. " +
                    "We had to buy some Syn Cards to be able to communicate in the new country. ",
            ),
    )
