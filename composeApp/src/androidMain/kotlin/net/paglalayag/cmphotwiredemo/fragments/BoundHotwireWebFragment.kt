package net.paglalayag.cmphotwiredemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment
import net.paglalayag.cmphotwiredemo.App
import net.paglalayag.cmphotwiredemo.R

@HotwireDestinationDeepLink(uri = "hotwire://fragment/bound_web")
class BoundHotwireWebFragment : HotwireWebFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.bound_hotwire_fragment_web, container, false)

        view.findViewById<ComposeView>(R.id.compose_web_view).apply {
            setContent {
                App()
            }
        }

        return view
    }
    fun setEpisodeFromBoundFragment(episodeUrl: String) {
        println("setting episode: $episodeUrl from Bound hotwire fragment")
    }
}

//    }
//    val viewModel by viewModel<HotwireWebHiddenViewModel>()
//    fun setEpisodeFromBoundFragment(episodeUrl: String) {
//        val setEpisodeUrl = HotwireWebAction.SetEpisodeURL(episodeUrl)
//        viewModel.onAction(setEpisodeUrl)
//    }
