package net.paglalayag.cmphotwiredemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.paglalayag.cmphotwiredemo.data.repository.DefaultPodcastRepository
import net.paglalayag.cmphotwiredemo.domain.PodcastsAction
import net.paglalayag.cmphotwiredemo.domain.PodcastsState

class PodcastsViewModel(
    private val podcastRepository: DefaultPodcastRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PodcastsState())
    val state = _state
        .onStart{}
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                _state.value
            )

    fun onAction(action: PodcastsAction) {
        when(action) {
            is PodcastsAction.SetEpisodeURL -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            episodeUrl = action.episodeUrl,
                            episodeDuration = action.episodeDuration.toLong(),
                            isFavorite = podcastRepository.isEpisodeFavorite(action.episodeUrl)
                        )
                    }
                }
            }
            is PodcastsAction.ToggleEpisodeURL -> {
                viewModelScope.launch {
                    if (state.value.isFavorite) {
                        podcastRepository.deleteFromFavorites(action.episodeUrl)
                    } else {
                        podcastRepository.markAsFavorite(action.episodeUrl)
                    }
                    _state.update {
                        it.copy(isFavorite = podcastRepository.isEpisodeFavorite(action.episodeUrl))
                    }
                }
            }
        }
    }
}