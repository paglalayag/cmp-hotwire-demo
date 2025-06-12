package net.paglalayag.cmphotwiredemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireFragment
import net.paglalayag.cmphotwiredemo.R
import net.paglalayag.cmphotwiredemo.presentation.FavoritesListScreenRoot
import net.paglalayag.cmphotwiredemo.presentation.PodcastsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@HotwireDestinationDeepLink(uri = "hotwire://fragment/favorites")
class FavoritesFragment : HotwireFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        view.findViewById<ComposeView>(R.id.compose_view).apply {
            setContent {
                FavoritesListView()
            }
        }
        return view
    }
}

@Composable
@Preview
fun FavoritesListView() {
    val viewModel = koinViewModel<PodcastsViewModel>()
    MaterialTheme {
        FavoritesListScreenRoot(
            viewModel = viewModel
        )
    }
}

