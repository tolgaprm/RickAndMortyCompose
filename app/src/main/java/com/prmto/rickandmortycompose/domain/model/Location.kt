package com.prmto.rickandmortycompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prmto.rickandmortycompose.util.Constant.LOCATION_TABLE_NAME

@Entity(tableName = LOCATION_TABLE_NAME)
data class Location(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val type: String
)
