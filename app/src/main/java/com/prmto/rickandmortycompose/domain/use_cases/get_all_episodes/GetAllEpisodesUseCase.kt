package com.prmto.rickandmortycompose.domain.use_cases.get_all_episodes

import androidx.paging.PagingData
import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.Episode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllEpisodesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Episode>> {
        return repository.getAllEpisodes()
    }
}