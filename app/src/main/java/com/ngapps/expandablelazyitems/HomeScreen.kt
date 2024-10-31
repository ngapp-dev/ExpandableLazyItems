package com.ngapps.expandablelazyitems

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ngapps.expandablelazyitems.model.Player
import com.ngapps.expandablelazyitems.ui.PlayerCategoryCard
import com.ngapps.expandablelazyitems.ui.PlayerResourceCard

@Composable
fun HomeScreen(
    players: List<Player>,
    modifier: Modifier = Modifier,
) {
    val playersByCategory = players.groupBy { it.sportCategory }

    var expandedState by rememberSaveable { mutableStateOf(listOf<String>()) }
    val state = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("home:screen"),
        state = state,
    ) {
        playersByCategory.forEach { (category, players) ->
            item(key = category, span = { GridItemSpan(maxLineSpan) }) {
                PlayerCategoryCard(
                    category = category,
                    onExpandClick = {
                        if (expandedState.contains(category)) {
                            expandedState -= category
                        } else {
                            expandedState += category
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            items(items = players, key = { it.id }) { player ->
                AnimatedVisibility(
                    visible = expandedState.contains(category),
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically(),
                ) {
                    PlayerResourceCard(
                        name = player.name,
                        team = player.team,
                        position = player.position,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        }
    }
}