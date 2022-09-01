package com.prmto.rickandmortycompose.domain.model.enums

import com.prmto.rickandmortycompose.util.Constant.LIST_NAME
import com.prmto.rickandmortycompose.util.Constant.VERTICAL_GRID_NAME

enum class ListType(val listName: String) {
    LIST(LIST_NAME),
    VERTICAL_GRID(VERTICAL_GRID_NAME)
}