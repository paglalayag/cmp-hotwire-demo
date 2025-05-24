package net.paglalayag.cmphotwiredemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import net.paglalayag.cmphotwiredemo.components.PlayBack
import net.paglalayag.cmphotwiredemo.core.presentation.DarkRed
import net.paglalayag.cmphotwiredemo.domain.PodcastsState
import net.paglalayag.cmphotwiredemo.presentation.PodcastsViewModel

@Composable
fun HotwireWebScreenRoot(
    viewModel: PodcastsViewModel
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
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            PlayBack(
                backgroundColor = DarkRed,
                duration = podcastsState.episodeDuration,
                audioFile = podcastsState.episodeUrl,
            )
        }
    }
}
