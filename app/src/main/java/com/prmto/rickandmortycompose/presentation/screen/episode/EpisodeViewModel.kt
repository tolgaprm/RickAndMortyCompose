package com.prmto.rickandmortycompose.presentation.screen.episode

import androidx.lifecycle.ViewModel
import androidx.paging.insertSeparators
import androidx.paging.map
import com.prmto.rickandmortycompose.domain.model.EpisodeListItem
import com.prmto.rickandmortycompose.domain.model.season
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val episodes = useCases.getAllEpisodesUseCase()
        .map { pagingData -> pagingData.map { EpisodeListItem.EpisodeItem(it) } }
        .map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    // end of the list
                    return@insertSeparators null
                }

                if (before == null) {
                    return@insertSeparators EpisodeListItem.SeparatorItem("Season 1")
                }

                if (before.season != after.season) {
                    return@insertSeparators EpisodeListItem.SeparatorItem("Season ${after.season}")
                } else {
                    null
                }
            }
        }

}