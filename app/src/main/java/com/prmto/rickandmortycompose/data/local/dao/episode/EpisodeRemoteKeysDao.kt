package com.prmto.rickandmortycompose.data.local.dao.episode

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prmto.rickandmortycompose.domain.model.remote_key.EpisodeRemoteKeys


@Dao
interface EpisodeRemoteKeysDao {

    @Query("SELECT * FROM episode_remote_keys_table WHERE id=:episodeId")
    suspend fun getRemoteKeys(episodeId: Int): EpisodeRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<EpisodeRemoteKeys>)

    @Query("DELETE FROM episode_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}