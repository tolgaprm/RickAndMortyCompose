package com.prmto.rickandmortycompose.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prmto.rickandmortycompose.R
import com.prmto.rickandmortycompose.domain.model.enums.GenderState
import com.prmto.rickandmortycompose.domain.model.enums.StatusState
import com.prmto.rickandmortycompose.presentation.screen.character.CharacterViewModel
import com.prmto.rickandmortycompose.presentation.ui.theme.BasicBlack
import com.prmto.rickandmortycompose.presentation.ui.theme.LARGE_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.MEDIUM_PADDING
import com.prmto.rickandmortycompose.presentation.ui.theme.buttonTextColor
import com.prmto.rickandmortycompose.util.MultiDevicePreviewWithOutSystemUI

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
    ) {

        BottomSheetHeader(
            onClickApplyButton = { characterViewModel.onClickApplyButton() },
            isFilter = characterViewModel.checkIfTheFilterHasBeenApplied(),
            onClickClearButton = {
                characterViewModel.clearTheFilter()
            }
        )

        Column(modifier = Modifier.padding(all = LARGE_PADDING)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = characterViewModel.characterNameQuery.value,
                onValueChange = { characterViewModel.onChangeCharacterQuery(name = it) },
                label = {
                    Text(text = stringResource(R.string.character_name))
                },
                singleLine = true,

                )

            Spacer(modifier = Modifier.height(MEDIUM_PADDING))

            BottomSheetSection<StatusState>(
                title = R.string.status,
                radioGroupItems = listOf(
                    StatusState.ALIVE,
                    StatusState.DEAD,
                    StatusState.UNKNOWN,
                    StatusState.NONE
                ),
                selectedOption = characterViewModel.statusState.value
            ) {
                characterViewModel.onStatusOptionState(statusState = it)
            }

            Spacer(modifier = Modifier.height(MEDIUM_PADDING))

            BottomSheetSection<GenderState>(
                title = R.string.gender,
                radioGroupItems = listOf(
                    GenderState.FEMALE,
                    GenderState.MALE,
                    GenderState.GENDERLESS,
                    GenderState.UNKNOWN,
                    GenderState.NONE
                ),
                selectedOption = characterViewModel.genderState.value
            ) {
                characterViewModel.onGenderOptionState(genderState = it)
            }

        }

    }
}

@Composable
fun BottomSheetHeader(
    onClickApplyButton: () -> Unit,
    onClickClearButton: () -> Unit,
    isFilter: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp)
            .padding(all = MEDIUM_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.filter),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )

        if (isFilter) {
            BottomSheetButton(
                buttonText = R.string.clear,
                onClickApplyButton = onClickClearButton
            )
        }
        BottomSheetButton(
            onClickApplyButton = onClickApplyButton,
            buttonText = R.string.apply
        )


    }
}

@Composable
fun BottomSheetButton(
    modifier: Modifier = Modifier,
    @StringRes buttonText: Int,
    onClickApplyButton: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClickApplyButton
    ) {
        Text(
            text = stringResource(buttonText).toUpperCase(locale = Locale.current),
            style = MaterialTheme.typography.button,
            fontSize = MaterialTheme.typography.button.fontSize,
            color = MaterialTheme.colors.buttonTextColor
        )
    }
}

@Composable
fun <T> BottomSheetSection(
    @StringRes title: Int,
    radioGroupItems: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.h6,
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.BasicBlack.copy(alpha = 0.8f)
    )

    Column(modifier = Modifier.selectableGroup()) {
        radioGroupItems.forEach { radioItem ->

            if (radioItem is StatusState && radioItem != StatusState.NONE || radioItem is GenderState && radioItem != GenderState.NONE) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .selectable(
                            selected = (radioItem == selectedOption),
                            onClick = { onOptionSelected(radioItem) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (radioItem == selectedOption),
                        onClick = { onOptionSelected(radioItem) }
                    )

                    if (radioItem is StatusState) {
                        Text(text = radioItem.title.capitalize(locale = Locale.current))
                    } else if (radioItem is GenderState) {
                        Text(text = radioItem.title.capitalize(locale = Locale.current))
                    }

                }
            }


        }


    }
}
