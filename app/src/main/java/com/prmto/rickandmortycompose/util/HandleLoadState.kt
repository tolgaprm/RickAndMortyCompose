package com.prmto.rickandmortycompose.util

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.navigation.Screen
import com.prmto.rickandmortycompose.presentation.comman.CharacterItemShimmerEffect
import com.prmto.rickandmortycompose.presentation.comman.CharacterShimmerEffectListType
import com.prmto.rickandmortycompose.presentation.comman.EpisodeItemShimmerEffect
import com.prmto.rickandmortycompose.presentation.comman.LocationShimmerEffect
import com.prmto.rickandmortycompose.presentation.components.ErrorScreen

@Composable
fun handleLoadState(
    loadState: CombinedLoadStates,
    onRetryClick: () -> Unit,
    listType: ListType? = null,
    whichScreen: Screen? = null,
    isEmptyList: Boolean
): Boolean {

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        else -> null
    }


    return when {
        loadState.refresh is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading -> {

            listType?.let {
                if (listType == ListType.HORIZONTAL_GRID) {
                    CharacterItemShimmerEffect()
                } else {
                    CharacterShimmerEffectListType()
                }
            }

            if (whichScreen == Screen.Episode) {
                EpisodeItemShimmerEffect()
            } else if (whichScreen == Screen.Location) {
                LocationShimmerEffect()
            }


            false
        }

        error != null && isEmptyList -> {
            ErrorScreen(
                onRetryClick = onRetryClick
            )
            false
        }


        else -> {
            true
        }
    }

}