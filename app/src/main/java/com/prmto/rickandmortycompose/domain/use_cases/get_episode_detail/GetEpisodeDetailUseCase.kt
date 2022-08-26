package com.prmto.rickandmortycompose.domain.use_cases.get_episode_detail

import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.EpisodeDetail
import com.prmto.rickandmortycompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(episodeId: Int): Flow<Resource<EpisodeDetail>> {
        return repository.getEpisodeDetail(episodeId = episodeId)
    }
}