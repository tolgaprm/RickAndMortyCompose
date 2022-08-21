package com.prmto.rickandmortycompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prmto.rickandmortycompose.data.local.dao.CharacterDao
import com.prmto.rickandmortycompose.data.local.dao.CharacterRemoteKeysDao
import com.prmto.rickandmortycompose.data.local.dao.LocationDao
import com.prmto.rickandmortycompose.data.local.dao.LocationRemoteKeysDao
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.model.remote_key.CharacterRemoteKeys
import com.prmto.rickandmortycompose.domain.model.remote_key.LocationRemoteKeys


@Database(
    entities = [Character::class, CharacterRemoteKeys::class, Location::class, LocationRemoteKeys::class],
    version = 1
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
    abstract fun locationDao(): LocationDao
    abstract fun locationRemoteKeysDao(): LocationRemoteKeysDao
}