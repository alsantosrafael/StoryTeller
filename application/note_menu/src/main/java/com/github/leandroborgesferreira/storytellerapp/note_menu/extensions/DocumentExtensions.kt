package com.github.leandroborgesferreira.storytellerapp.note_menu.extensions

import com.github.leandroborgesferreira.storytellerapp.note_menu.ui.dto.DocumentUi
import com.github.leandroborgesferreira.storyteller.model.document.Document
import com.github.leandroborgesferreira.storyteller.parse.PreviewParser

fun Document.toUiCard(previewParser: PreviewParser, selected: Boolean = false): DocumentUi =
    DocumentUi(
        documentId = id,
        title = title,
        lastEdit = "",
        preview = content?.values?.let(previewParser::preview) ?: emptyList(),
        selected = selected
    )