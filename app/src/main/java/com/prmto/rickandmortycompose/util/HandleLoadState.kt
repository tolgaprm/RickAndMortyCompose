package com.prmto.rickandmortycompose.util

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.prmto.rickandmortycompose.domain.model.enums.ListType
import com.prmto.rickandmortycompose.navigation.Screen
import com.prmto.rickandmortycompose.presentation.comman.CharacterItemShimmerEffect
import com.prmto.rickandmortycompose.presentation.comman.CharacterShimmerEffectListType
import com.prmto.rickandmortycompose.presentation.comman.EpisodeItemShimmerEffect
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



    loadState.mediator?.let {
        Log.d("HandleError", "MEdiator")
        Log.d("HandleError", loadState.mediator?.refresh.toString())
        Log.d("HandleError", loadState.mediator?.append.toString())
        Log.d("HandleError", loadState.mediator?.prepend.toString())
        Log.d("HandleError", "LOADSTATE")

        Log.d("HandleError", loadState.refresh.toString())
        Log.d("HandleError", loadState.append.toString())
        Log.d("HandleError", loadState.prepend.toString())
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
                CircularProgressIndicator()
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