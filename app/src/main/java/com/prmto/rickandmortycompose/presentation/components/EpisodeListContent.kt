package com.prmto.rickandmortycompose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.prmto.rickandmortycompose.domain.model.Episode
import com.prmto.rickandmortycompose.domain.model.EpisodeListItem
import com.prmto.rickandmortycompose.presentation.ui.theme.*

@Composable
fun EpisodeListContent(
    modifier: Modifier = Modifier,
    episodes: LazyPagingItems<EpisodeListItem>,
    onClickEpisodeItem: (episodeId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = LARGE_PADDING,
            start = LARGE_PADDING,
            end = LARGE_PADDING,
            bottom = BOTTOM_NAV_PADDING
        ),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(episodes) {
            it?.let {
                EpisodeItem(
                    episode = it,
                    onClickEpisodeItem = onClickEpisodeItem
                )
            }
        }
    }
}


@Composable
fun EpisodeItem(
    modifier: Modifier = Modifier,
    episode: EpisodeListItem,
    onClickEpisodeItem: (episodeId: Int) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (episode is EpisodeListItem.SeparatorItem) {
            EpisodeSeparatorItem(seasonText = episode.season)
        } else {
            val episodeItem = episode as EpisodeListItem.EpisodeItem
            EpisodeItemContent(
                episode = episodeItem.episode,
                onClickEpisodeItem = onClickEpisodeItem
            )
        }
    }
}

@Composable
fun EpisodeSeparatorItem(
    modifier: Modifier = Modifier,
    seasonText: String
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
        Text(
            modifier = modifier,
            text = seasonText,
            style = MaterialTheme.typography.h5,
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EpisodeItemContent(
    modifier: Modifier = Modifier,
    episode: Episode,
    onClickEpisodeItem: (episodeId: Int) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.medium)
            .clickable {
                onClickEpisodeItem(episode.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(
                    0.7f
                )
                .padding(SMALL_PADDING)
        ) {
            Text(
                modifier = Modifier.paddingFromBaseline(
                    top = MEDIUM_PADDING
                ),
                text = episode.episode,
                style = MaterialTheme.typography.h6,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.BasicBlack
            )


            Text(
                modifier = Modifier.paddingFromBaseline(
                    top = MEDIUM_PADDING,
                    bottom = MEDIUM_PADDING
                ),
                text = episode.name,
                style = MaterialTheme.typography.body1,
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = MaterialTheme.colors.episodeItemNameColor
            )

            Text(
                text = episode.air_date,
                style = MaterialTheme.typography.caption.copy(letterSpacing = 1.5.sp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = MaterialTheme.colors.episodeItemNameColor
            )

            Spacer(modifier = Modifier.height(MEDIUM_PADDING))
            Divider()
            Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        }

        IconButton(onClick = { onClickEpisodeItem(episode.id) }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}