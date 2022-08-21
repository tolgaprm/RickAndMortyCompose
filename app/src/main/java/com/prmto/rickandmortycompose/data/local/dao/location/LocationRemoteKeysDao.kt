package com.prmto.rickandmortycompose.data.local.dao.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prmto.rickandmortycompose.domain.model.remote_key.LocationRemoteKeys

@Dao
interface LocationRemoteKeysDao {

    @Query("SELECT * FROM location_remote_keys_table WHERE id=:locationId")
    suspend fun getRemoteKeys(locationId: Int): LocationRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<LocationRemoteKeys>)

    @Query("DELETE FROM location_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}