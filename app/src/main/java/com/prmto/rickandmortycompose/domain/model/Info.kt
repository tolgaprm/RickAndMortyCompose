package com.prmto.rickandmortycompose.domain.model

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
