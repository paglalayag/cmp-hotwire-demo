package net.paglalayag.cmphotwiredemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vinceglb.filekit.list
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.paglalayag.cmphotwiredemo.data.repository.DefaultPodcastRepository
import net.paglalayag.cmphotwiredemo.domain.Podcast
import net.paglalayag.cmphotwiredemo.domain.PodcastsAction
import net.paglalayag.cmphotwiredemo.domain.PodcastsState

class PodcastsViewModel(
    private val podcastRepository: DefaultPodcastRepository
) : ViewModel() {
    private var observeFavoriteJob: Job? = null
    private val _state = MutableStateFlow(PodcastsState())
    val state = _state
        .onStart{
            observeFavoriteStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: PodcastsAction) {
        when(action) {
            is PodcastsAction.SetEpisodeURL -> {
                viewModelScope.launch {
                    println("SetEpisodeURL before update ${state.value}")
                    _state.update {
                        it.copy(
                            episodeUrl = action.episodeUrl,
                            episodeDuration = action.episodeDuration.toLong(),
                            isFavorite = isEpisodeFavorite(action.episodeUrl),
                            episodeAudiofile = setEpisodeForPlayer(action.episodeUrl)
                        )
                    }
                }
            }
            is PodcastsAction.ToggleEpisodeURL -> {
                viewModelScope.launch {
                    if (state.value.isFavorite) {
                        podcastRepository.deleteFromFavorites(state.value.episodeUrl)
                        _state.update {
                            it.copy(
                                episodeAudiofile = setEpisodeForPlayer(action.episodeUrl)
                            )
                        }
                    } else {
                        podcastRepository.markAsFavorite(
                            state.value.episodeUrl,
                            state.value.episodeDuration
                        )
                        _state.update {
                            it.copy(
                                episodeAudiofile = setEpisodeForPlayer(action.episodeUrl)
                            )
                        }
                    }
                }
            }
            is PodcastsAction.IsEpisodeFavorite -> {
                viewModelScope.launch {
                    println("IsEpisodeFavorite response from viewmodel ${state.value.favoritePodcasts}")

                    _state.update {
                        it.copy(
                            isFavorite = isEpisodeFavorite(action.episodeUrl)
                        )
                    }
                }
            }
        }
    }
    private fun observeFavoriteStatus() {
        observeFavoriteJob?.cancel()
        observeFavoriteJob = podcastRepository.getFavoritePodcasts()
            .onEach { favoritePodcasts ->
                _state.update {
                    it.copy(
                        favoritePodcasts = favoritePodcasts
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun isEpisodeFavorite(episodeUrl: String) : Boolean {
        val res = state.value.favoritePodcasts
            .any { podcast ->
                podcast.episodeUrl == episodeUrl
            }

        return res
    }

    suspend fun setEpisodeForPlayer(episodeUrl: String) : String {
        println("inside setEpisodeForPlayer repo, $episodeUrl")
        return if(isEpisodeFavorite(episodeUrl)) {
            podcastRepository.getLocalAudioFilePath(episodeUrl)
        } else {
            episodeUrl
        }
    }

}