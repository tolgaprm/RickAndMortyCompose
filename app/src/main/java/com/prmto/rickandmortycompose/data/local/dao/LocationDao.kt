package com.prmto.rickandmortycompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prmto.rickandmortycompose.domain.model.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table ORDER BY id ASC")
    fun getAllLocations(): PagingSource<Int, Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocations(locations: List<Location>)

    @Query("DELETE FROM location_table")
    fun deleteLocations()
}