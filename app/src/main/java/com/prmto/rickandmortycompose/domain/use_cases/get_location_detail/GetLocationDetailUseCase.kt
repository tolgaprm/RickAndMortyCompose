package com.prmto.rickandmortycompose.domain.use_cases.get_location_detail

import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.LocationDetail
import com.prmto.rickandmortycompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(locationId: Int): Flow<Resource<LocationDetail>> {
        return repository.getLocation(locationId = locationId)
    }
}