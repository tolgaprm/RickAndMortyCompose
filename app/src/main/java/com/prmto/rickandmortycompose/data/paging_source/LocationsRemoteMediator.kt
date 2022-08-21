package com.prmto.rickandmortycompose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.prmto.rickandmortycompose.data.RickAndMortyAPI
import com.prmto.rickandmortycompose.data.local.RickAndMortyDatabase
import com.prmto.rickandmortycompose.data.remote.dto.toLocation
import com.prmto.rickandmortycompose.domain.model.Location
import com.prmto.rickandmortycompose.domain.model.remote_key.LocationRemoteKeys
import com.prmto.rickandmortycompose.util.Constant.LOCATION_STARTING_PAGE_INDEX
import javax.inject.Inject

@ExperimentalPagingApi
class LocationsRemoteMediator @Inject constructor(
    private val rickAndMortyAPI: RickAndMortyAPI,
    private val database: RickAndMortyDatabase
) : RemoteMediator<Int, Location>() {

    private val locationDao = database.locationDao()
    private val locationRemoteKeysDao = database.locationRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Location>
    ): MediatorResult {

        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    val page = remoteKeys?.nextPage?.minus(1) ?: LOCATION_STARTING_PAGE_INDEX
                    page
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
            }

            val response = rickAndMortyAPI.getAllLocations(page = page)

            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        locationDao.deleteLocations()
                        locationRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val keys = response.results.map { location ->
                        LocationRemoteKeys(
                            id = location.id,
                            prevPage = response.info.prev?.split("=")?.get(1)?.toInt(),
                            nextPage = response.info.next?.split("=")?.get(1)?.toInt()
                        )
                    }

                    locationDao.addLocations(response.results.toLocation())
                    locationRemoteKeysDao.addRemoteKeys(remoteKeys = keys)
                }

            }

            MediatorResult.Success(endOfPaginationReached = response.info.next == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Location>): LocationRemoteKeys? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(anchorPosition = it)?.id?.let { locationId ->
                locationRemoteKeysDao.getRemoteKeys(locationId = locationId)
            }
        }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, Location>): LocationRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { location ->
                locationRemoteKeysDao.getRemoteKeys(locationId = location.id)
            }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Location>): LocationRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { location ->
                locationRemoteKeysDao.getRemoteKeys(locationId = location.id)
            }
    }
}

