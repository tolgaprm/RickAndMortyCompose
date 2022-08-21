package com.prmto.rickandmortycompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prmto.rickandmortycompose.util.Constant.EPISODE_TABLE_NAME

@Entity(tableName = EPISODE_TABLE_NAME)
data class Episode(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
)
