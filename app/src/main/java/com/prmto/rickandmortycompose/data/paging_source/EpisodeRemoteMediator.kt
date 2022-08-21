package com.prmto.rickandmortycompose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.prmto.rickandmortycompose.data.RickAndMortyAPI
import com.prmto.rickandmortycompose.data.local.RickAndMortyDatabase
import com.prmto.rickandmortycompose.data.remote.dto.toEpisode
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.remote_key.EpisodeRemoteKeys
import com.prmto.rickandmortycompose.util.Constant
import javax.inject.Inject

@ExperimentalPagingApi
class EpisodeRemoteMediator @Inject constructor(
    private val rickAndMortyAPI: RickAndMortyAPI,
    private val database: RickAndMortyDatabase
) : RemoteMediator<Int, Episode>() {

    private val episodeDao = database.episodeDao()
    private val episodeRemoteKeysDao = database.episodeRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Episode>
    ): MediatorResult {

        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: Constant.CHARACTER_STARTING_PAGE_INDEX
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

            val response = rickAndMortyAPI.getAllEpisodes(page = page)

            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        episodeDao.deleteAllEpisodes()
                        episodeRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val keys = response.results.map {
                        EpisodeRemoteKeys(
                            id = it.id,
                            nextPage = response.info.next?.split("=")?.get(1)?.toInt(),
                            prevPage = response.info.prev?.split("=")?.get(1)?.toInt(),
                        )
                    }
                    episodeDao.addAllEpisodes(response.results.toEpisode())
                    episodeRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.info.next == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Episode>): EpisodeRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episode ->
                episodeRemoteKeysDao.getRemoteKeys(episodeId = episode.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Episode>): EpisodeRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { episode ->
                episodeRemoteKeysDao.getRemoteKeys(episodeId = episode.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Episode>): EpisodeRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                episodeRemoteKeysDao.getRemoteKeys(episodeId = id)
            }
        }
    }
}