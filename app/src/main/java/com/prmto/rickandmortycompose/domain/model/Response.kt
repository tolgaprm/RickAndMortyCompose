package com.prmto.rickandmortycompose.domain.model

data class Response<T>(
    val info:Info,
    val results :List<T>
)
