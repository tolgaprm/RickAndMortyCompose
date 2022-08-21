package com.prmto.rickandmortycompose.domain.model.remote_key

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prmto.rickandmortycompose.util.Constant.LOCATION_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = LOCATION_REMOTE_KEYS_DATABASE_TABLE)
data class LocationRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
) {
}