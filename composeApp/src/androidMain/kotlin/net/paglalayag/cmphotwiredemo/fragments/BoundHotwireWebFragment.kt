package net.paglalayag.cmphotwiredemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment
import net.paglalayag.cmphotwiredemo.presentation.HotwireWebScreenRoot
import net.paglalayag.cmphotwiredemo.R
import net.paglalayag.cmphotwiredemo.components.ConnectMessageData
import net.paglalayag.cmphotwiredemo.components.ToggleMessageData
import net.paglalayag.cmphotwiredemo.domain.PodcastsAction
import net.paglalayag.cmphotwiredemo.presentation.PodcastsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@HotwireDestinationDeepLink(uri = "hotwire://fragment/bound_web")
class BoundHotwireWebFragment : HotwireWebFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel by viewModel<PodcastsViewModel>()
        val view = inflater.inflate(R.layout.bound_hotwire_fragment_web, container, false)

        view.findViewById<ComposeView>(R.id.compose_web_view).apply {
            setContent {
                HotwireWebScreenRoot(viewModel)
            }
        }

        return view
    }
    val viewModel by viewModel<PodcastsViewModel>()
    fun setEpisodeFromBoundFragment(episodeData: ConnectMessageData) {
        println("setting episode: ${episodeData.episodeUrl} from Bound hotwire fragment")
        val setEpisodeUrl = PodcastsAction.SetEpisodeURL(episodeData.episodeUrl, episodeData.episodeDuration)
        viewModel.onAction(setEpisodeUrl)
    }

    fun checkIfEpisodeIsFavorite(episodeUrl: String) :Boolean {
        println("checking for favorite: ${episodeUrl} from Bound hotwire fragment")
        val isEpisodeFavorite = PodcastsAction.IsEpisodeFavorite(episodeUrl)
        viewModel.onAction(isEpisodeFavorite)
        return viewModel.state.value.isFavorite
    }

    fun toggleEpisodeFromBoundFragment(episodeData: ToggleMessageData) {
        println("toggling episode: ${episodeData.episodeUrl} from Bound hotwire fragment")
        val toggleEpisodeUrl = PodcastsAction.ToggleEpisodeURL(episodeData.episodeUrl)
        viewModel.onAction(toggleEpisodeUrl)
    }
}
