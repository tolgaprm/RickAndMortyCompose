package com.prmto.rickandmortycompose.presentation.screen.location

import androidx.lifecycle.ViewModel
import com.prmto.rickandmortycompose.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val locations = useCases.getAllLocationsUseCase()
}