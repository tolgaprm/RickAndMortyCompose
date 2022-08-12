package com.prmto.rickandmortycompose.domain.model

data class LocationDetail(
    val id: Int,
    val name: String,
    val type: String,
    val residents: List<String>,
)
