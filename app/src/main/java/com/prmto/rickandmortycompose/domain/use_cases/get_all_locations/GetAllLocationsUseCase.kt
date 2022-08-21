package com.prmto.rickandmortycompose.domain.use_cases.get_all_locations

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocationsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Location>> {
        return repository.getAllLocations()
    }
}