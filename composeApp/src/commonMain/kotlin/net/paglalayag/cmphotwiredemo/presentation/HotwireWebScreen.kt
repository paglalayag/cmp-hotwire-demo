package net.paglalayag.cmphotwiredemo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import net.paglalayag.cmphotwiredemo.components.PlayBack
import net.paglalayag.cmphotwiredemo.core.presentation.Beige
import net.paglalayag.cmphotwiredemo.core.presentation.DarkRed
import net.paglalayag.cmphotwiredemo.domain.PodcastsState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HotwireWebScreenRoot(
    viewModel: PodcastsViewModel = koinViewModel()
) {
    val podcastsState by viewModel.state.collectAsStateWithLifecycle()
    HotwireWebScreen(
        podcastsState = podcastsState
    )
}

@Composable
@Preview
fun HotwireWebScreen(
    podcastsState: PodcastsState
) {
    MaterialTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .background(DarkRed),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PlayBack(
                backgroundColor = Beige,
                duration = podcastsState.episodeDuration,
                audioFile = podcastsState.episodeAudiofile,
            )
        }
    }
}
