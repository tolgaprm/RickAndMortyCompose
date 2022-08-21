package com.prmto.rickandmortycompose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.prmto.rickandmortycompose.data.RickAndMortyAPI
import com.prmto.rickandmortycompose.data.local.RickAndMortyDatabase
import com.prmto.rickandmortycompose.data.remote.dto.toCharacter
import com.prmto.rickandmortycompose.domain.model.Character
import com.prmto.rickandmortycompose.domain.model.remote_key.CharacterRemoteKeys
import com.prmto.rickandmortycompose.util.Constant.CHARACTER_STARTING_PAGE_INDEX
import javax.inject.Inject

@ExperimentalPagingApi
class CharactersRemoteMediator @Inject constructor(
    private val rickAndMortyApi: RickAndMortyAPI,
    private val database: RickAndMortyDatabase
) : RemoteMediator<Int, Character>() {


    private val characterDao = database.characterDao()
    private val characterRemoteKeysDao = database.characterRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: CHARACTER_STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = rickAndMortyApi.getAllCharacters(page = page)

            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAllCharacters()
                        characterRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val keys = response.results.map { character ->
                        CharacterRemoteKeys(
                            id = character.id,
                            prevPage = response.info.prev?.split("=")?.get(1)?.toInt(),
                            nextPage = response.info.next?.split("=")?.get(1)?.toInt(),
                        )
                    }

                    characterRemoteKeysDao.addAllRemoteKeys(characterRemoteKeys = keys)
                    characterDao.addCharacters(response.results.toCharacter())

                }
            }

            MediatorResult.Success(endOfPaginationReached = response.info.next == null)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterRemoteKeysDao.getRemoteKeys(characterId = id)
            }
        }
    }
}

