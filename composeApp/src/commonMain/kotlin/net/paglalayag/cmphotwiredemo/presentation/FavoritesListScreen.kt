package net.paglalayag.cmphotwiredemo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import net.paglalayag.cmphotwiredemo.core.presentation.DarkRed
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_hotwire_demo.composeapp.generated.resources.Res
import cmp_hotwire_demo.composeapp.generated.resources.no_favorites
import net.paglalayag.cmphotwiredemo.components.PlayBack
import net.paglalayag.cmphotwiredemo.core.presentation.Beige
import net.paglalayag.cmphotwiredemo.core.presentation.LightGrey
import net.paglalayag.cmphotwiredemo.domain.PodcastsAction
import net.paglalayag.cmphotwiredemo.domain.PodcastsState
import net.paglalayag.cmphotwiredemo.presentation.components.PodcastList
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesListScreenRoot(
    viewModel: PodcastsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoritesListScreen(
        podcastListState = state,
        onAction = { action ->
            when(action) {
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Preview
@Composable
fun FavoritesListScreen(
    podcastListState: PodcastsState,
    onAction: (PodcastsAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkRed)
    ) {
        PlayBack(
            backgroundColor = Beige,
            playerState = podcastListState
            )
        Surface(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = 8.dp,
                    horizontal = 24.dp
                ),
            color = Beige.copy(alpha = 0.8f),
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (podcastListState.favoritePodcasts.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.no_favorites),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = LightGrey
                    )
                } else {
                    PodcastList(
                        podcasts = podcastListState.favoritePodcasts,
                        onPodcastClick = {
                            onAction(PodcastsAction.OnPodcastClick(it))
                        },
                        modifier = Modifier
                            .padding(top = 12.dp),
                    )
                }
            }
        }
    }
}
