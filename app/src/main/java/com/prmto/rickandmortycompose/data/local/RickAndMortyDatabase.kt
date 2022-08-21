package com.prmto.rickandmortycompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prmto.rickandmortycompose.data.local.dao.character.CharacterDao
import com.prmto.rickandmortycompose.data.local.dao.character.CharacterRemoteKeysDao
import com.prmto.rickandmortycompose.data.local.dao.episode.EpisodeDao
import com.prmto.rickandmortycompose.data.local.dao.episode.EpisodeRemoteKeysDao
import com.prmto.rickandmortycompose.data.local.dao.location.LocationDao
import com.prmto.rickandmortycompose.data.local.dao.location.LocationRemoteKeysDao
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.model.remote_key.CharacterRemoteKeys
import com.prmto.rickandmortycompose.domain.model.remote_key.EpisodeRemoteKeys
import com.prmto.rickandmortycompose.domain.model.remote_key.LocationRemoteKeys


@Database(
    entities = [
        Character::class, CharacterRemoteKeys::class,
        Location::class, LocationRemoteKeys::class,
        Episode::class, EpisodeRemoteKeys::class
    ],
    version = 1
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
    abstract fun locationDao(): LocationDao
    abstract fun locationRemoteKeysDao(): LocationRemoteKeysDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun episodeRemoteKeysDao(): EpisodeRemoteKeysDao
}