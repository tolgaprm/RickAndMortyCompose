package com.prmto.rickandmortycompose.presentation.screen.location

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun LocationScreen(
    locationViewModel: LocationViewModel = hiltViewModel()
) {
    val locations = locationViewModel.locations.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(20.dp)
    ) {
        items(locations) {
            if (it != null) {
                Text(
                    text = it.name,
                    fontSize = 20.sp
                )
            }
        }
    }
}