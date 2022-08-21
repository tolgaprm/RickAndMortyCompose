package com.prmto.rickandmortycompose.data.local.dao.episode

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prmto.rickandmortycompose.domain.model.Episode

@Dao
interface EpisodeDao {

    @Query("SELECT  * FROM episode_table ORDER BY id ASC")
    fun getAllEpisodes(): PagingSource<Int, Episode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllEpisodes(episodes: List<Episode>)

    @Query("DELETE FROM episode_table")
    suspend fun deleteAllEpisodes()
}