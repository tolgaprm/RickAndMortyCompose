package com.prmto.rickandmortycompose.domain.use_cases.get_episode

import com.prmto.rickandmortycompose.data.repository.Repository
import com.prmto.rickandmortycompose.domain.model.Episode
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(episodeId: Int): Episode {
        return repository.getEpisode(episodeId = episodeId)
    }
}