package net.paglalayag.cmphotwiredemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.paglalayag.cmphotwiredemo.domain.PodcastsState

@Preview
@Composable
fun MainScreenPreview() {
    HotwireWebScreen(
        podcastsState = PodcastsState(
            episodeUrl = "https://paglalayag.net/assets/audio/jay-paglalayag-audio.mp3",
            episodeAudiofile = "",
            episodeDuration = 345670
        )
    )
}
