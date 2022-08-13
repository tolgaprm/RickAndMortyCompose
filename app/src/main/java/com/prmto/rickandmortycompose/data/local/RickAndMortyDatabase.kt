package com.prmto.rickandmortycompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prmto.rickandmortycompose.data.local.dao.CharacterDao
import com.prmto.rickandmortycompose.data.local.dao.CharacterRemoteKeysDao
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.CharacterRemoteKeys


@Database(entities = [Character::class, CharacterRemoteKeys::class], version = 1)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
}